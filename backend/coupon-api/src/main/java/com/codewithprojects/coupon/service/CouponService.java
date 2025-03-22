package com.codewithprojects.coupon.service;

import com.codewithprojects.coupon.dto.ConsumeRequestDTO;
import com.codewithprojects.coupon.dto.CouponDTO;
import com.codewithprojects.coupon.dto.CouponConsumptionHistoryDTO;
import com.codewithprojects.coupon.dto.CouponValidationResponseDTO;

import java.util.List;

public interface CouponService {

    CouponDTO addCoupon(CouponDTO couponDTO);

    void deleteCoupon(Long id);

    void deleteCouponByCode(String code);

    List<CouponDTO> getAllCoupons();

    List<CouponConsumptionHistoryDTO> getCouponHistory();

    CouponValidationResponseDTO validateCoupon(String code);

    boolean consumeCoupon(ConsumeRequestDTO consumeRequestDTO);

    CouponDTO getCouponByCode(String code);
}
