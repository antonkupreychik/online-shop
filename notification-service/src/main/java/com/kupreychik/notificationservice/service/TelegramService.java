package com.kupreychik.notificationservice.service;

public interface TelegramService {

    void sendMessage(Long userId, String text);
}
