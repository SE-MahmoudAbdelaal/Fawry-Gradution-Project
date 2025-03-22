package com.e_commerce.notification_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
        @Id
        private Long productId;
//        @Transient
//        private Long id;
        private String name;
        @NotNull
        private double price;
        private String description;
        private String merchantCardNumber;
        private String merchantEmail;
        private int quantity;
        private List<Item> items;
}
