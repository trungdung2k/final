package com.example.finalexam.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ListAccountRequest {

    private Long departmentId;

    @ApiModelProperty("userName")
    private String userName;

    @ApiModelProperty(value = "sort")
    private String sort; // createdDate

    @ApiModelProperty(value = "isAsc")
    private boolean isAsc;

    @ApiModelProperty(value = "createdDate")
    private Date createdDate;

    private int page;

    private int size;

}
