package com.healthcare.auth.controller;

import com.healthcare.auth.dto.ApiResponse;
import com.healthcare.auth.dto.LoginRequest;
import com.healthcare.auth.dto.LoginResponseDto;
import com.healthcare.auth.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> authenticate(@RequestBody LoginRequest loginRequest){
        LoginResponseDto loginResponseDto = authenticationService.authenticate(loginRequest);
        ApiResponse<LoginResponseDto> response = new ApiResponse<>(
                true,
                "",
                loginResponseDto
        );

        return ResponseEntity
                .status(200)
                .body(response);
    }

}
