package com.healthcare.auth.service;

import com.healthcare.auth.dto.LoginRequest;
import com.healthcare.auth.dto.LoginResponseDto;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Override
    public LoginResponseDto authenticate(LoginRequest loginRequest) {
        return null;
    }
}
