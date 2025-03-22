package com.codewithprojects.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO {
    private Long id;
    private String code;
    private Integer usageCount;
    private Integer maxUsages;
    private LocalDate expiryDate;
    private double value;
    private String valueType;
}
