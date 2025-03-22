package com.e_commerce.order_api.rest;


import com.e_commerce.order_api.entity.Item;
import com.e_commerce.order_api.entity.Order;
import com.e_commerce.order_api.entity.User;
import com.e_commerce.order_api.exception.CustomException;
import com.e_commerce.order_api.feginClient.CouponClient;
import com.e_commerce.order_api.feginClient.UserClient;
import com.e_commerce.order_api.header.HeaderGenerator;
import com.e_commerce.order_api.model.CouponRequest;
import com.e_commerce.order_api.model.CouponResponse;
import com.e_commerce.order_api.model.OrderDto;
import com.e_commerce.order_api.model.OrderNotification;
import com.e_commerce.order_api.service.*;
import com.e_commerce.order_api.utilitie.OrderUtilities;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shop/order")
public class OrderResource {
    @Autowired
    CouponService couponService;
    @Autowired
    private HeaderGenerator headerGenerator;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserClient userClient;
    @Autowired
    NotificationService notificationService;

    @PostMapping(value = "{userId}")
    public ResponseEntity<Order> saveOrder(@PathVariable("userId") Long userId,
                                           @RequestHeader(value = "cartid") String cartId,
                                           @RequestBody OrderDto orderDto,
                                           HttpServletRequest request) {
        List<Item> cart = cartService.getAllItemsFromCart(cartId);
        String cardNumber = orderDto.getCardNumber();
        String cvv = orderDto.getCvv();
        String couponCode = orderDto.getCoupon();
        User user = userClient.getUserById(userId, request.getHeader("Authorization"));
//        if (cart != null && user != null && cvv != null && cardNumber != null) {
          if (!cart.isEmpty() && cvv != null && cardNumber != null) {
              Order order = this.createOrder(cart, cardNumber,cvv);
              order.setCustomerId(userId);
              order.setTotal_amount(OrderUtilities.countTotalPrice(cart));
              CouponResponse coupon = couponService.couponIsValid(couponCode);
            if (coupon != null) {
                order.setTotal_amount_after_discount(OrderUtilities.countTotalPriceWithCoupon(cart, coupon.getValueType(), coupon.getValue()));
            }else{
                order.setTotal_amount_after_discount(order.getTotal_amount());
            }
            try {
                Order savedOrder = orderService.saveOrder(order);
                if (savedOrder != null) {
                    CouponRequest consumeOrderRequest = new CouponRequest();
                    consumeOrderRequest.setCode(couponCode);
                    consumeOrderRequest.setOrder_id(order.getId());
                    consumeOrderRequest.setCustomer_id(userId);
                    couponService.couponConsumption(consumeOrderRequest);
                    cartService.deleteCart(cartId);
                    notificationService.sendOrderNotification(savedOrder,user.getEmail());

                    return new ResponseEntity<Order>(savedOrder, headerGenerator.getHeadersForSuccessPostMethod(request, order.getId()),
                            HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<Order>(headerGenerator.getHeadersForError(),
                            HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                new CustomException(e.getMessage());
                return new ResponseEntity<Order>(headerGenerator.getHeadersForError(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Order>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
    }

//    private Order createOrder(List<Item> cart, User user, String cardNumber, String cvv) {
private Order createOrder(List<Item> cart, String cardNumber,String cvv) {
        Order order = new Order();
        order.setItems(cart);
//        order.setUser(user);
        order.setCard_number(cardNumber);
        order.setCvv(cvv);
        order.setCreatedAt(LocalDate.now());
        order.setStatus("PAYMENT_EXPECTED");
        order.setTotal_amount(OrderUtilities.countTotalPrice(cart));
        return order;
    }

    @GetMapping("/search/{customerId}")
    public List<Order> findOrderByDateRange(
            @RequestParam ("startDate") @DateTimeFormat(pattern = "MM-DD-YY") LocalDate startDate,
            @RequestParam ("endDate") @DateTimeFormat(pattern = "MM-DD-YY") LocalDate endDate,
    @PathVariable ("customerId") Long customerId) {
            return orderService.searchOrdersByDateRange(startDate, endDate ,customerId );
    }

    @GetMapping("/searchById/{customerId}")
    public ResponseEntity<List<Order>> findOrderByCustomerId(@PathVariable ("customerId") Long customerId) {
        return ResponseEntity.ok(orderService.findOrderByCustomerId(customerId));
    }
}