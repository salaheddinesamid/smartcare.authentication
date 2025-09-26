package com.healthcare.auth.service;

import com.healthcare.auth.dto.LoginRequest;
import com.healthcare.auth.dto.LoginResponseDto;

public interface AuthenticationService {

    /**
     * This method handles user authentication
     * @param loginRequest
     * @return a response containing access and refresh tokens
     */
    LoginResponseDto authenticate(LoginRequest loginRequest);
}
