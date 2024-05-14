package com.inkacode.scrapsicoes.controller;

import com.inkacode.scrapsicoes.domain.Item;
import com.inkacode.scrapsicoes.dto.ItemFilterDto;
import com.inkacode.scrapsicoes.service.IItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ItemController.BASE_CTRL_URL)
@RequiredArgsConstructor
public class ItemController {
    public static final String BASE_CTRL_URL = "api/v1/items";
    private final IItemService itemService;


    @GetMapping
    public Page<Item> getAllItems(
            @RequestParam(value = "page", defaultValue = "0") @Valid @PositiveOrZero int page,
            @RequestParam(value = "limit", defaultValue = "10") @Valid @Positive int limit,
            @RequestParam(value = "search", defaultValue = "") String search
            ) {
        ItemFilterDto filterDto = ItemFilterDto.builder()
                .search(search)
                .page(page < 0 ? 0 : page)
                .limit(limit)
                .build();
        return itemService.getAllItems(filterDto);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Item> getItemById(@PathVariable String id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.saveItem(item));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item item) {
        return ResponseEntity.ok(itemService.updateItem(item));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable String id) {
        
    }

}
