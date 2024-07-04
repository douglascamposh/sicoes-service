package com.inkacode.scrapsicoes.repository;

import com.inkacode.scrapsicoes.domain.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITokenRepository extends MongoRepository<Token, String> {
    Token findByToken(String jwt);
    Token findByUserId(String userId);
}
