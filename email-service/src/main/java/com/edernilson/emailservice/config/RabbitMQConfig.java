package com.edernilson.emailservice.config;

import com.edernilson.emailservice.EmailServiceApplication;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EmailServiceApplication.EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueue() {
        return new Queue(EmailServiceApplication.QUEUE_NAME);
    }

    @Bean
    public Binding declareBindingGeneric() {
        return BindingBuilder.bind(appQueue()).to(appExchange()).with(EmailServiceApplication.ROUTING_KEY);
    }

    @Bean
    public Binding declareBindingSpecific() {
        return BindingBuilder.bind(appQueue()).to(appExchange()).with(EmailServiceApplication.ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
