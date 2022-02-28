package com.example.finalexam.controllers;

import com.example.finalexam.factory.PageResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.finalexam.request.AccountRequest;
import com.example.finalexam.request.ListAccountRequest;
import com.example.finalexam.service.AccountService;

import javax.validation.Valid;

@RestController
public class AccountController extends PageController{

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "List account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List account successfully"),
            @ApiResponse(code = 400, message = "Bad com.example.finalexam.request"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server Error")
    })
//    @PreAuthorize("hasAnyAuthority('R0LE_ADMIN')")
    @GetMapping("/account/list_account")
    public ResponseEntity<PageResponse> getListAccount (ListAccountRequest request){
        return new ResponseEntity<>(buildPageResponse(accountService.listAccount(request)), HttpStatus.OK);
    }

    @ApiOperation(value =  "create account user " )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 400, message = "Bad com.example.finalexam.request"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server Error")
    })
//    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEES')")
    @PostMapping("/account")
    public ResponseEntity<Void>  createAccountUser(@Valid @RequestBody AccountRequest request){
        accountService.createAccountUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value =  "update account user" )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 400, message = "Bad com.example.finalexam.request"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server Error")
    })
//    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEES')")
    @PutMapping("/account")
    public ResponseEntity<Void>  updateAccount(@Valid @RequestBody AccountRequest request){
        accountService.updateAccountUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value =  "delete account " )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 400, message = "Bad com.example.finalexam.request"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server Error")
    })
//    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEES','R0LE_ADMIN')")
    @DeleteMapping("/account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
