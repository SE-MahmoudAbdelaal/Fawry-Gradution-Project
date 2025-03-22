//package com.e_commerce.notification_api.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.MappingJackson2MessageConverter;
//import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
//import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
//
//@Configuration
//public class RabbitMQConfig {
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//    @Value("${spring.rabbitmq.port}")
//    private int port;
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//    private static final String EXCHANGE_NAME = "retry-exchange";
//    public static final String QUEUE = "orderQueue";
//    private static final String RETRY_QUEUE = "orderQueue.retry";
//    public static final String UNDELIVERED_QUEUE = "orderQueue.undelivered";
//    public static final String ROUTING_KEY = "order_routing_key";
//    @Value("${rabbitmq.retry.delay-in-ms}")
//    private Integer retryDelay;
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(messageConverter());
//        return rabbitTemplate;
//    }
////    @Bean
////    MessageHandlerMethodFactory messageHandlerMethodFactory() {
////        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
////        messageHandlerMethodFactory.setMessageConverter(new MappingJackson2MessageConverter());
////        return messageHandlerMethodFactory;
////    }
//
//    @Bean
//    public Jackson2JsonMessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public TopicExchange exchange(){
//        return new TopicExchange(EXCHANGE_NAME);
//    }
//
//    @Bean
//    public Queue orderQueue(){
//        return QueueBuilder.durable(QUEUE)
//                .deadLetterExchange(EXCHANGE_NAME)
//                .deadLetterRoutingKey(RETRY_QUEUE)
//                .quorum()
//                .build();
//    }
//
//    @Bean
//    public Queue retryQueue(){
//        return QueueBuilder.durable(RETRY_QUEUE)
//                .deadLetterExchange(EXCHANGE_NAME)
//                .deadLetterRoutingKey(UNDELIVERED_QUEUE)
//                .ttl(retryDelay)
//                .quorum()
//                .build();
//    }
//
//    @Bean
//    public Queue undeliveredQueue(){
//        return QueueBuilder.durable(UNDELIVERED_QUEUE)
//                .quorum()
//                .build();
//    }
//
//        @Bean
//    public Binding orderBinding(Queue orderQueue , TopicExchange exchange ){
//        return  BindingBuilder.bind(orderQueue).to(exchange).with(ROUTING_KEY);
//    }
//
//    @Bean
//    public Binding retryBinding(Queue retryQueue , TopicExchange exchange ){
//        return  BindingBuilder.bind(retryQueue).to(exchange).with(RETRY_QUEUE);
//    }
//    @Bean
//    public Binding undeliveredBinding(Queue undeliveredQueue , TopicExchange exchange ){
//        return  BindingBuilder.bind(undeliveredQueue).to(exchange).with(UNDELIVERED_QUEUE);
//    }
//}
package com.e_commerce.notification_api.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    public static final String EXCHANGE_NAME = "retry-exchange";
    public static final String QUEUE = "orderQueue";
    private static final String RETRY_QUEUE = "orderQueue.retry";
    public static final String UNDELIVERED_QUEUE = "orderQueue.undelivered";
    public static final String ROUTING_KEY = "order_routing_key";

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(QUEUE)
                .deadLetterExchange(EXCHANGE_NAME)
                .deadLetterRoutingKey(RETRY_QUEUE)
                .build();
    }

    @Bean
    public Queue retryQueue() {
        return QueueBuilder.durable(RETRY_QUEUE)
                .deadLetterExchange(EXCHANGE_NAME)
                .deadLetterRoutingKey(UNDELIVERED_QUEUE)
                .ttl(60000)  // الوقت المناسب (بالملي ثانية) لإعادة المحاولة
                .build();
    }

    @Bean
    public Queue undeliveredQueue() {
        return QueueBuilder.durable(UNDELIVERED_QUEUE)
                .build();
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding retryBinding(Queue retryQueue, TopicExchange exchange) {
        return BindingBuilder.bind(retryQueue).to(exchange).with(RETRY_QUEUE);
    }

    @Bean
    public Binding undeliveredBinding(Queue undeliveredQueue, TopicExchange exchange) {
        return BindingBuilder.bind(undeliveredQueue).to(exchange).with(UNDELIVERED_QUEUE);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
