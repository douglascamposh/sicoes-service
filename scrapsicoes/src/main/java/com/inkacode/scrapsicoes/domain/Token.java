package com.inkacode.scrapsicoes.domain;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("token")
public class Token {
    @Id
    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String token;
}
