package com.kupreychik.orderservice.service;

public interface KafkaService {

    void sendMessage(String topic, String message);
}
