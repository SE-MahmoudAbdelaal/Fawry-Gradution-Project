//package com.e_commerce.notification_api.service.Impl;
//
//import com.e_commerce.notification_api.entity.Notification;
//import com.e_commerce.notification_api.repo.NotificationRepository;
//import com.e_commerce.notification_api.service.NotificationService;
//import com.e_commerce.notification_api.service.ResendNotificationsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//@Component
//public class ResendNotificationsServiceImpl implements ResendNotificationsService {
//    @Autowired
//    private NotificationService notificationService;
//    @Autowired
//    private NotificationRepository notificationRepository;
//    @Scheduled(fixedRate = 600000)
//    @Override
//    public void resendFailedNotifications() {
//        List<Notification> failedNotifications = notificationRepository.findByStatus("FAILED");
//        for(Notification notification:failedNotifications){
//            try{
//            notificationService.resendNotification(notification);
//            notification.setStatus("SENT");
//            }catch (Exception e){
//                notification.setStatus("FAILED");
//            }
//            notificationRepository.save(notification);
//        }
//    }
//}
