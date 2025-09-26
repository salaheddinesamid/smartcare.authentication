package com.healthcare.auth.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String accessToken;
    private String refreshToken;
}
