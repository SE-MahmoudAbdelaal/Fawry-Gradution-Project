package com.e_commerce.order_api.utilitie;

import com.e_commerce.order_api.entity.Product;

import java.math.BigDecimal;

public class CartUtilities {
    public static Double getSubTotalForItem(Product product, int quantity){
        return (product.getPrice()) * (quantity);
    }
}