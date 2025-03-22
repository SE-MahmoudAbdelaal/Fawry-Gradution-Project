package com.example.servicestoreproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stock_quantity;
    private long store_id;
    private String image_url;
    private String merchantCardNumber;
    private String merchantEmail;

}
