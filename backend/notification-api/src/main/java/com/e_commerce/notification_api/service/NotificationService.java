package com.e_commerce.notification_api.service;

import com.e_commerce.notification_api.entity.Notification;
import com.e_commerce.notification_api.model.Order;

public interface NotificationService {
    public void sendNotificationToMerchant(Order order);
    public void sendNotificationToCustomer(Order order, String customerEmail);
//    public void resendNotification(Notification notification);
}
