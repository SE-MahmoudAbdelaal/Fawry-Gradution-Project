package com.e_commerce.notification_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private String card_number;
    private double total_amount;
    private double  total_amount_after_discount;
    private Long customerId;
    private LocalDate createdAt;
    private List<Item> items;

}
