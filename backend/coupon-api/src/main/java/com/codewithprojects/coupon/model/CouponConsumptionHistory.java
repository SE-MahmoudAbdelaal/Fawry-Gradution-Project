package com.codewithprojects.coupon.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "coupon_consumption_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponConsumptionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private Long customer_id;

    private Long order_id;

    private LocalDateTime consumptionDate;
}