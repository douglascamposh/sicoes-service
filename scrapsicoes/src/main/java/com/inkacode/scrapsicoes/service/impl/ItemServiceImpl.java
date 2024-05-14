package com.inkacode.scrapsicoes.service.impl;

import com.inkacode.scrapsicoes.domain.Item;
import com.inkacode.scrapsicoes.dto.ItemFilterDto;
import com.inkacode.scrapsicoes.repository.ItemCustomRepository;
import com.inkacode.scrapsicoes.repository.ItemRepository;
import com.inkacode.scrapsicoes.service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements IItemService {

    private final ItemRepository itemRepository;
    private final ItemCustomRepository itemCustomRepository;

    @Override
    public Page<Item> getAllItems(ItemFilterDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getPage(), filterDto.getLimit());
        return itemCustomRepository.searchItems(filterDto.getSearch(), pageable);
    }

    @Override
    public Item findById(String id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            return  itemOptional.get();
        }
        throw new RuntimeException("The Item was not found.");
    }

    @Override
    public Item saveItem(Item item) {
        Item exist = itemRepository.findItemByCuce(item.getCuce());
        if (exist != null) {
            throw new RuntimeException("Unable create, already exist an Item with duplicated Cuce.");
        }
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        findById(item.getId());
        return itemRepository.save(item);
    }
}
