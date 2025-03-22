package com.e_commerce.order_api.service;

import com.e_commerce.order_api.model.CouponRequest;
import com.e_commerce.order_api.model.CouponResponse;
import org.springframework.http.ResponseEntity;

public interface CouponService {
    public CouponResponse couponIsValid(String couponCode);
    public void couponConsumption(CouponRequest consumeCouponRequest);
}
