package com.inkacode.scrapsicoes.service;

import com.inkacode.scrapsicoes.domain.Item;
import com.inkacode.scrapsicoes.dto.ItemFilterDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IItemService {

    Page<Item> getAllItems(ItemFilterDto filterDto);
    Item findById(String id);
    Item saveItem(Item item);
    Item updateItem(Item item);
    void deleteItem(String id);
}
