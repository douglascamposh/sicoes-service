package com.inkacode.scrapsicoes.service;

import com.inkacode.scrapsicoes.dto.JwtAuthResponse;
import com.inkacode.scrapsicoes.dto.SigninRequest;
import com.inkacode.scrapsicoes.dto.SignupRequest;
import com.inkacode.scrapsicoes.dto.UserDTO;

public interface IUserService {
    UserDTO findByEmail(String email);
    JwtAuthResponse signup(SignupRequest signupRequest);
    JwtAuthResponse signin(SigninRequest signinRequest);
}
