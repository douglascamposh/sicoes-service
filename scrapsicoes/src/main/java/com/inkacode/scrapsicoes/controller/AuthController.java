package com.inkacode.scrapsicoes.controller;

import com.inkacode.scrapsicoes.dto.JwtAuthResponse;
import com.inkacode.scrapsicoes.dto.SigninRequest;
import com.inkacode.scrapsicoes.dto.SignupRequest;
import com.inkacode.scrapsicoes.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(AuthController.BASE_CTRL_URL)
public class AuthController {
    public static final String BASE_CTRL_URL = "api/v1/auth";
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final IUserService userService;

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthResponse singUp(@Valid @RequestBody SignupRequest signupRequest) {
        logger.info("Trying sign in with email: " + signupRequest.getEmail());
        return userService.signup(signupRequest);
    }
    @PostMapping("/logIn")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthResponse login(@Valid @RequestBody SigninRequest signinRequest) {
        logger.info("Trying to log in with email: " + signinRequest.getEmail());
        return userService.signin(signinRequest);
    }
}
