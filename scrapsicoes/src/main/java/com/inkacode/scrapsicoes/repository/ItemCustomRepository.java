package com.inkacode.scrapsicoes.repository;

import com.inkacode.scrapsicoes.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemCustomRepository {
    Page<Item> searchItems(String query, Pageable pageable);
}
