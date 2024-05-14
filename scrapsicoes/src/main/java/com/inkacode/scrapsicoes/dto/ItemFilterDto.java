package com.inkacode.scrapsicoes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemFilterDto {
    private String search;
    private int page = 0;
    private int limit = 10;
}
