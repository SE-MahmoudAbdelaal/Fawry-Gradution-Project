package com.e_commerce.order_api.feginClient;

import com.e_commerce.order_api.entity.User;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-API")//, url = "http://localhost:8086/")
@Headers("Authorization: {token}")
public interface UserClient {
        @GetMapping(value = "/users/{id}")
        public User getUserById(@PathVariable("id") Long id, @RequestHeader("Authorization") String bearerToken);
}
