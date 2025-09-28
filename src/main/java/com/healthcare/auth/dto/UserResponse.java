package com.healthcare.auth.dto;

import lombok.Data;

@Data
public class UserResponse {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String password;
}
