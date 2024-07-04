package com.kupreychik.notificationservice.service.impl;

import com.kupreychik.notificationservice.config.OnlineShopBot;
import com.kupreychik.notificationservice.model.entity.Notification;
import com.kupreychik.notificationservice.model.enums.NotificationProvider;
import com.kupreychik.notificationservice.model.enums.NotificationType;
import com.kupreychik.notificationservice.repository.NotificationRepository;
import com.kupreychik.notificationservice.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {

    private final OnlineShopBot bot;
    private final NotificationRepository notificationRepository;
    private final UserDataServiceImpl userDataService;


    @Override
    public void sendMessage(Long userId, String text) {

        bot.sendCustomMessage(
                Long.parseLong(userDataService.getDataByUserIdAndProvider(userId, NotificationProvider.TELEGRAM)),
                text
        );

        notificationRepository.save(Notification.builder()
                .success(true)
                .data(text)
                .provider(NotificationProvider.TELEGRAM)
                .type(NotificationType.ORDER_CREATED)
                .build());
        log.info("Message sent successfully and saved to mongoDB");
    }


}
