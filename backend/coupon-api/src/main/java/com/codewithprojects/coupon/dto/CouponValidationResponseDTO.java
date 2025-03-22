package com.codewithprojects.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CouponValidationResponseDTO {
    private boolean valid;
    private double value;
    private String valueType;
    private String message;
}
