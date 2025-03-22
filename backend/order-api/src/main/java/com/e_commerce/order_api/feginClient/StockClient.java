package com.e_commerce.order_api.feginClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "STORE-API")//, url = "http://localhost:8084/stock")
public interface StockClient {
        @GetMapping(value = "/stock/isavailable/{productId}")
        public ResponseEntity<Boolean> checkAvailability(@PathVariable("productId") Long productId);
        @PostMapping(value = "/stock/consume/{productId}")
        public void consumeStock(@PathVariable("productId") Long productId , @RequestParam int amount);
        @PostMapping(value = "/stock/add/{productId}")
        public void addToStock(@PathVariable("productId") Long productId, @RequestParam int amount);
}