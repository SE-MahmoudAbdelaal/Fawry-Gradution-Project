package com.e_commerce.notification_api.consume;

import com.e_commerce.notification_api.model.NotificationResponse;
import com.e_commerce.notification_api.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.e_commerce.notification_api.config.RabbitMQConfig.QUEUE;
import static com.e_commerce.notification_api.config.RabbitMQConfig.UNDELIVERED_QUEUE;

@Component
public class OrderConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.retry.count}")
    private Integer retryCount;
    @Autowired
    NotificationService notificationService;
    @RabbitListener(queues = QUEUE)
    public void primary( NotificationResponse notificationResponse, @Header(required = false, name = "x-death") Map<String, ?> xDeath){
        // Log the raw message body
        System.out.println("Received notification for order: " + notificationResponse.getOrder().getId());

            notificationService.sendNotificationToMerchant(notificationResponse.getOrder());
            notificationService.sendNotificationToCustomer(notificationResponse.getOrder(),notificationResponse.getCustomerEmail());
            System.out.println(notificationResponse.getOrder().getCard_number());
            if(checkRetryCount(xDeath)){
                sendToUndelivered(notificationResponse);
            }
    }
    private boolean checkRetryCount(Map<String,?> xDeath){
        if (xDeath != null && !xDeath.isEmpty()){
            List<Map<String, ?>> deathHeader = (List<Map<String, ?>>) xDeath.get("x-death");
            if (deathHeader != null && !deathHeader.isEmpty()) {
                Long count = (Long) deathHeader.get(0).get("count");
              return count>= retryCount;
        }
        }
        return false;
    }
    private void sendToUndelivered(NotificationResponse notificationResponse){
        System.out.println("Maximum retry reached, sending message to the undelivered queue.");
       this.rabbitTemplate.convertAndSend(UNDELIVERED_QUEUE,notificationResponse);
    }
}
