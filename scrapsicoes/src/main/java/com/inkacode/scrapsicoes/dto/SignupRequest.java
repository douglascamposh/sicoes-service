package com.inkacode.scrapsicoes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest extends UserDTO {
    @NotBlank
    private String password;
}
