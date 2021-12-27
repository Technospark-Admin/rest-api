package com.technospark.api.restapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String name;
    private double price;
    private long categoryId;
}
