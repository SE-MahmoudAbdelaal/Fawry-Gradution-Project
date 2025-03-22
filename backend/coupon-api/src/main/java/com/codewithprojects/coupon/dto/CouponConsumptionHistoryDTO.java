package com.codewithprojects.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponConsumptionHistoryDTO {
    private Long id;
    private Long couponId;
    private Long customer_id;
    private Long order_id;
    private LocalDateTime consumptionDate;
}
