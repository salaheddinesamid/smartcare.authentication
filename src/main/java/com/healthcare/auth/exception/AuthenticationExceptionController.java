package com.healthcare.auth.exception;

import com.healthcare.auth.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationExceptionController {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentials(BadCredentialsException e){
        ApiResponse<?> response = new ApiResponse<>(
                false,
                "Bad credentials, email or password is incorrect",
                null
        );

        return ResponseEntity
                .status(403)
                .body(response);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentials(AuthenticationFailedException e){
        ApiResponse<?> response = new ApiResponse<>(
                false,
                "Authentication failed",
                null
        );

        return ResponseEntity
                .status(500)
                .body(response);
    }
}
