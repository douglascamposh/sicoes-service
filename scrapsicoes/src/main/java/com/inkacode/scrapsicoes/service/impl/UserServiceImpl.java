package com.inkacode.scrapsicoes.service.impl;

import com.inkacode.scrapsicoes.domain.User;
import com.inkacode.scrapsicoes.dto.JwtAuthResponse;
import com.inkacode.scrapsicoes.dto.SigninRequest;
import com.inkacode.scrapsicoes.dto.SignupRequest;
import com.inkacode.scrapsicoes.dto.UserDTO;
import com.inkacode.scrapsicoes.enums.Role;
import com.inkacode.scrapsicoes.error.DataNotFoundException;
import com.inkacode.scrapsicoes.error.InternalErrorException;
import com.inkacode.scrapsicoes.repository.IUserRepository;
import com.inkacode.scrapsicoes.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passworEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            return modelMapper.map(userOptional.get(), UserDTO.class);
        }
        throw new DataNotFoundException("Unable to get User with email: " + email);
    }

    @Override
    public JwtAuthResponse signup(SignupRequest signupRequest) {
        if (userRepository.findUserByEmail(signupRequest.getEmail()).isPresent()) {
            throw new InternalErrorException("User with email: " + signupRequest.getEmail() + " already exists");
        }
        User user = modelMapper.map(signupRequest, User.class);
        user.setRole(Role.USER.toString());//Todo: change to List of roles
        user.setPassword(passworEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword())
        );
        User user = userRepository.findUserByEmail(signinRequest.getEmail()).orElseThrow(() -> new InternalErrorException("User not found"));
        String jwt = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();

    }
}
