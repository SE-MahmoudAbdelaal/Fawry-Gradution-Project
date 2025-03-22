package com.example.product_api.Mapper;

import com.example.product_api.dto.ProductDto;
import com.example.product_api.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
}
