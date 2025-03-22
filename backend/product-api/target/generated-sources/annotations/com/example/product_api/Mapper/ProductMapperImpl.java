package com.example.product_api.Mapper;

import com.example.product_api.dto.ProductDto;
import com.example.product_api.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T12:55:13+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setDescription( product.getDescription() );
        productDto.setPrice( product.getPrice() );
        productDto.setStock_quantity( product.getStock_quantity() );
        productDto.setStore_id( product.getStore_id() );
        productDto.setImage_url( product.getImage_url() );
        productDto.setMerchantCardNumber( product.getMerchantCardNumber() );
        productDto.setCvv( product.getCvv() );
        productDto.setUserId( product.getUserId() );

        return productDto;
    }

    @Override
    public Product toProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setDescription( productDto.getDescription() );
        product.setPrice( productDto.getPrice() );
        product.setStock_quantity( productDto.getStock_quantity() );
        product.setStore_id( productDto.getStore_id() );
        product.setImage_url( productDto.getImage_url() );
        product.setMerchantCardNumber( productDto.getMerchantCardNumber() );
        product.setCvv( productDto.getCvv() );
        product.setUserId( productDto.getUserId() );

        return product;
    }
}
