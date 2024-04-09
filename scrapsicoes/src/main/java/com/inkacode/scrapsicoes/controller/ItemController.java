package com.inkacode.scrapsicoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ItemController.BASE_CTRL_URL)
public class ItemController {
    public static final String BASE_CTRL_URL = "api/v1/items";

    @GetMapping
    public String prueba() {
        return "es una prueba";
    }

}
