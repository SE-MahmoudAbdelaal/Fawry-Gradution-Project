package com.e_commerce.order_api.model;

import com.e_commerce.order_api.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderNotification {
    private Order order;
    private String customerEmail;
    private LocalDate localDate;
}
