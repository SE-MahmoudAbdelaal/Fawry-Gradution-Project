package com.codewithprojects.coupon.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CouponValidationResponse {
    private boolean valid;
    private double value;
    private String valueType;
    private String message;
}