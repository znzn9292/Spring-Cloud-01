package com.znzn.userservice.dto;


import com.znzn.userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String password;
    private String userId;
    private Date createAt;

    private String encrytedPassword;

    private List<ResponseOrder> orders;
}
