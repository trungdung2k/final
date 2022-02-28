package com.example.finalexam.response;

import com.example.finalexam.entity.Account;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ListAccountResponse {


    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("userName")
    private String userName;

    @ApiModelProperty("fistName")
    private String firstName;

    @ApiModelProperty("createdDate")
    private Date createdDate;

    @ApiModelProperty("lastName")
    private String lastName;


    public ListAccountResponse(Account account) {
        this.id = account.getId();
        this.userName = account.getUserName();
        this.firstName = account.getFirstName();
        this.createdDate = account.getCreatedDate();
        this.lastName = account.getLastName();
    }

}
