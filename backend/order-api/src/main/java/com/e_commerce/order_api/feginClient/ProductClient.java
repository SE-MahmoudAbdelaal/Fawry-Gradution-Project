package com.e_commerce.order_api.feginClient;

import com.e_commerce.order_api.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-API")//, url = "http://localhost:8080/")
public interface ProductClient {
        @GetMapping(value = "/products/{id}")
        public Product getProductById(@PathVariable(value = "id") Long productId);
}
