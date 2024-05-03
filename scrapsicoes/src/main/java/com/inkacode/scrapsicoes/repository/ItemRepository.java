package com.inkacode.scrapsicoes.repository;

import com.inkacode.scrapsicoes.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
    Item findItemByCuce(String cuce);
}
