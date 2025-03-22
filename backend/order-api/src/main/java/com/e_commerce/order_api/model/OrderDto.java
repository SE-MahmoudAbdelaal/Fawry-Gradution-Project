package com.e_commerce.order_api.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    String coupon;
    @NotNull
    String cardNumber;
    @NotNull
    String cvv;
}
