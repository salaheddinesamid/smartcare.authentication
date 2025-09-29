package com.healthcare.auth.service;

import com.healthcare.auth.dto.ApiResponse;
import com.healthcare.auth.dto.LoginRequest;
import com.healthcare.auth.dto.LoginResponseDto;
import com.healthcare.auth.dto.UserResponse;
import com.healthcare.auth.exception.AuthenticationFailedException;
import com.healthcare.auth.exception.BadCredentialsException;
import com.healthcare.auth.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate;

    @Autowired
    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, RestTemplate restTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.restTemplate = restTemplate;
    }

    @Override
    public LoginResponseDto authenticate(LoginRequest loginRequest) {
       try{
           boolean userVerified = verifyUser(loginRequest.getEmail(),loginRequest.getPassword());
           if(!userVerified){
               throw new BadCredentialsException();
           }
           // Generate the token for authenticated user:
           String token = jwtUtil.generateToken(loginRequest.getEmail(),"");

           return new LoginResponseDto(
                   token,
                   null
           );


       }catch(AuthenticationFailedException exception){
           throw new AuthenticationFailedException();
       }

    }

    public boolean verifyUser(String email, String password){
        String uri = "http://localhost:8080/api/user/verify?email="+email+"&password="+password;
        ResponseEntity<ApiResponse<Boolean>> response = restTemplate
                .exchange(
                        uri,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ApiResponse<Boolean>>() {}
                );

        return response.getBody().getData();
    }

    private boolean verifyCredentials(UserResponse user, String password){
        return passwordEncoder
                .matches(password,user.getPassword());

    }
}
