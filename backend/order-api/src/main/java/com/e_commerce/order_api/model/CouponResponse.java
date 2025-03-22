package com.e_commerce.order_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponse {
    private boolean valid;
    private double value;
    private String valueType;
}
