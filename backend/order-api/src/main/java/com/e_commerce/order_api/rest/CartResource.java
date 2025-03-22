package com.e_commerce.order_api.rest;

import com.e_commerce.order_api.header.HeaderGenerator;
import com.e_commerce.order_api.model.CartDto;
import com.e_commerce.order_api.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(
        origins = {
                "http://localhost:4200"
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
@RestController
@RequestMapping("shop/cart")
public class CartResource {
    @Autowired
    CartService cartService;
    HeaderGenerator headerGenerator;
    @Autowired
    public CartResource(HeaderGenerator headerGenerator) {
        this.headerGenerator = headerGenerator;
    }

    @GetMapping
    public ResponseEntity<List<Object>> getCart(@RequestHeader(value = "Cookie") String cartId){
        List<Object>cart = cartService.getCart(cartId);
        if(!cart.isEmpty()){
            return new ResponseEntity<List<Object>>(
                    cart,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<List<Object>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }
//    @PostMapping(params = {"productId","quantity"})
    @PostMapping
    public ResponseEntity<List<Object>> addItemToCart(
            @RequestBody CartDto cartDto,
            HttpServletRequest request) {
        Long productId = cartDto.getProductId();
        System.out.println(productId);
        int quantity = cartDto.getQuantity();
        System.out.println(quantity);
        String cartId = cartDto.getCartId();
        System.out.println(cartId);
        List<Object> cart = cartService.getCart(cartId);
        if (cart != null) {
            if (cart.isEmpty()) {
                cartService.addItemToCart(cartId, productId, quantity);
            } else {
                if (cartService.checkIfItemIsExist(cartId, productId)) {
                    cartService.changeItemQuantity(cartId, productId, quantity);
                } else {
                    cartService.addItemToCart(cartId, productId, quantity);
                }
            }
            return new ResponseEntity<List<Object>>(cart,
                    headerGenerator.getHeadersForSuccessPostMethod(request, Long.parseLong(cartId)),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<List<Object>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("{productId}")
    public ResponseEntity<Void>removeItemFromCart(
            @PathVariable(value = "productId") Long productId,
            @RequestHeader(value = "cartid") String cartId,
    HttpServletRequest request){
        List<Object>cart = cartService.getCart(cartId);
        if(cart!=null){
            cartService.deleteItemFromCart(cartId,productId);
            return new ResponseEntity<Void>( headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<Void>(headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }
}
