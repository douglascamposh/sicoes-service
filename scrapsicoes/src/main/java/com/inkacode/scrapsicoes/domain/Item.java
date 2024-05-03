package com.inkacode.scrapsicoes.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "item")
public class Item {
    @Id
    private String id;
    @NotBlank
    private String cuce;
    private String entity;
    private String contract;
    private String modality;
    private String contractDescription;
    private Boolean auction;
    private String stateAuction;
    private Long publishDateItem;
    private Long presentationDate;
    private Long awardDate;
}
