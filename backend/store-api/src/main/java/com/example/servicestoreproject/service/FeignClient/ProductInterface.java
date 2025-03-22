package com.example.servicestoreproject.service.FeignClient;

import com.example.servicestoreproject.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-api-main",url = "http://localhost:8080/")
public interface ProductInterface {
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") long id);
    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product);
    @GetMapping("/products/{id}/isavailable")
    public boolean isavailable(@PathVariable("id") long id);

}
