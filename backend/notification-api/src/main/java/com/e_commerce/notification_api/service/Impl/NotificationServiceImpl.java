package com.e_commerce.notification_api.service.Impl;

import com.e_commerce.notification_api.entity.Notification;
import com.e_commerce.notification_api.model.Item;
import com.e_commerce.notification_api.model.Order;
import com.e_commerce.notification_api.repo.NotificationRepository;
import com.e_commerce.notification_api.service.EmailService;
import com.e_commerce.notification_api.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public void sendNotificationToMerchant(Order order) {
        Notification notification= new Notification();
        for (Item item : order.getItems()){
            notification.setRecipient(item.getProduct().getMerchantEmail());
            notification.setMessage("You have a new order: " +item.getQuantity()+ " "+ item.getProduct().getName() + " and we deposit order amount in your bank account");
            notification.setStatus("PENDING");
            notificationRepository.save(notification);
            try{
                emailService.sendEmail(item.getProduct().getMerchantEmail(),"New Order",notification.getMessage());
                notification.setStatus("SENT");
            }catch (Exception e){
                notification.setStatus("FAILED");
            }
            notificationRepository.save(notification);
        }
    }

    @Override
    public void sendNotificationToCustomer(Order order , String customerEmail ){
    Notification notification = new Notification();
    notification.setRecipient(customerEmail);
    notification.setMessage("Thank you for your order: " + order.getId() + " we withdraw order amount from your bank account");
    notification.setStatus("PENDING");
    notificationRepository.save(notification);
    try {
        emailService.sendEmail(customerEmail, "Order Confirmation", notification.getMessage());
        notification.setStatus("SENT");
    }catch (Exception e) {
        notification.setStatus("FAILED");
    }
        notificationRepository.save(notification);
    }

//    @Override
//    public void resendNotification(Notification notification) {
//        try{
//            emailService.sendEmail(notification.getRecipient(),"Resending Notification",notification.getMessage());
//            notification.setStatus("SENT");
//        }catch (Exception e){
//            notification.setStatus("FAILED");
//        }
//        notificationRepository.save(notification);
//    }
}
