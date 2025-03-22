package com.e_commerce.order_api.feginClient;

import com.e_commerce.order_api.model.CouponRequest;
import com.e_commerce.order_api.model.CouponResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "COUPON-API")//, url = "http://localhost:8082/")
public interface CouponClient {
    @GetMapping(value = "coupons/validate/{code}")
    public ResponseEntity<CouponResponse> getCouponByCode(@PathVariable(value = "code") String couponCode);
    @PostMapping(value = "coupons/consume")
    public void consumeCoupon(@RequestBody CouponRequest couponRequest );
}