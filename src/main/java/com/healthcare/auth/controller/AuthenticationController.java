package com.healthcare.auth.controller;

import com.healthcare.auth.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @PostMapping("login")
    public ResponseEntity<ApiResponse<?>> authenticate(){

    }

    @PostMapping("register")

}
