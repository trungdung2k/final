package com.example.finalexam.service;

import org.springframework.data.domain.Page;
import com.example.finalexam.request.AccountRequest;
import com.example.finalexam.request.ListAccountRequest;
import com.example.finalexam.response.ListAccountResponse;

import java.util.List;

public interface AccountService {
    Page<ListAccountResponse> listAccount(ListAccountRequest request);

    void createAccountUser(AccountRequest request);

    void updateAccountUser(AccountRequest request);

    void deleteAccount(Long id);

    List<ListAccountResponse> listAccountByFirstName(String firstName);
}
