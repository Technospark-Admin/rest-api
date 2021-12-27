package com.technospark.api.restapi.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequestDTO {

    private Long id ;
    private  String name ;
    private double price;


}
