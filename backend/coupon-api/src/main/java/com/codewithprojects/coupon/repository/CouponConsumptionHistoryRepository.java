package com.codewithprojects.coupon.repository;

import com.codewithprojects.coupon.model.CouponConsumptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponConsumptionHistoryRepository extends JpaRepository<CouponConsumptionHistory, Long> {
}