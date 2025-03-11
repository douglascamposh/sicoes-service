package com.inkacode.scrapsicoes.repository;

import com.inkacode.scrapsicoes.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface ItemRepository extends MongoRepository<Item, String> {
    Item findItemByCuce(String cuce);
    Page<Item> findByAuctionIsTrue(Pageable pageable);
    Page<Item> findByAuctionIsFalse(Pageable pageable);
}
