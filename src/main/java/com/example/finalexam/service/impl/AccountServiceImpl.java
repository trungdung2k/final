package com.example.finalexam.service.impl;

import com.example.finalexam.constant.MessageConst;
import com.example.finalexam.entity.Account;
import com.example.finalexam.entity.Department;
import com.example.finalexam.exception.BasicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.finalexam.repository.AccountRepository;
import com.example.finalexam.repository.DepartmentRepository;
import com.example.finalexam.request.AccountRequest;
import com.example.finalexam.request.ListAccountRequest;
import com.example.finalexam.response.ListAccountResponse;
import com.example.finalexam.service.AccountService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    public final AccountRepository accountRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,  DepartmentRepository departmentRepository) {
        this.accountRepository = accountRepository;
        this.departmentRepository = departmentRepository;
    }


    @Override
    public void createAccountUser(AccountRequest request) {

        // 1. Validate department
        Optional<Department> departmentOptional = departmentRepository.findById(request.getDepartmentId());
        if (!departmentOptional.isPresent()) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.DEPARTMENT_NOT_FOUND)
                    .addErrors(MessageConst.DEPARTMENT_E0001);
        }

        // 2. Create account
        Account account = request.asCreateAccount();
        account.setDepartment(departmentOptional.get());
        accountRepository.save(account);
    }

    @Override
    public void updateAccountUser(AccountRequest request) {
        // 1. Validate department
        Optional<Department> departmentOptional = departmentRepository.findById(request.getDepartmentId());
        if (!departmentOptional.isPresent()) {
            // throw Exception
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.NOT_FOUND)
                    .addErrors(MessageConst.ACCOUNT_E0006);
        }

        Account account = getAccount(request.getId());
        // 2. Validate account có bị trùng username (username là unique)
        // Chú ý: bỏ qua account đang thực hiện
        // existsByUsername(String username) -> kiểm tra tất cả các account
        // existsByUsernameAndIdNot(String username, Long id) -> kiểm tra tất cả các account ngoại trừ ID
        if (accountRepository.existsByUserNameAndIdNot(request.getUserName(), account.getId())){
                throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.USERNAME_ACCOUNT_EXITS)
                        .addErrors(MessageConst.ACCOUNT_E0003);
        }
        // 3. Cập nhật
        account = request.asUpdateAccount(account);
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {

        Account account = getAccount(id);
        if (Objects.isNull(account) ) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.NOT_FOUND)
                    .addErrors(MessageConst.ACCOUNT_E0006);
        }
        accountRepository.delete(account);
    }

    @Override
    public Page<ListAccountResponse> listAccount(ListAccountRequest request) {
        Page<ListAccountResponse> responses = accountRepository.listAccount(request);
        return responses;
    }

    @Override
    public List<ListAccountResponse> listAccountByFirstName(String firstName){
        List<ListAccountResponse> responses = accountRepository.listAccountByFirstName(firstName);

        return responses;
    }


    private Account getAccount(Long id) {
        if (Objects.isNull(id)) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.ID_ACCOUNT_EMPTY)
                    .addErrors(MessageConst.ACCOUNT_E0007);
        }

        Optional<Account> accountOptional = accountRepository.findByIdAndDeletedFalse(id);
        if (!accountOptional.isPresent()) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.NOT_FOUND)
                    .addErrors(MessageConst.ACCOUNT_E0006);
        }

        return accountOptional.get();
    }
}
