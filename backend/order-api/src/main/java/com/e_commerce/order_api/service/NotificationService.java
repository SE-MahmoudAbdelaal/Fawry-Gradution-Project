package com.e_commerce.order_api.service;

import com.e_commerce.order_api.entity.Order;
import com.e_commerce.order_api.model.OrderNotification;

public interface NotificationService {
    public void sendOrderNotification (Order order, String customerEmail);
}
