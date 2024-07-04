package com.kupreychik.notificationservice.service.impl;

import com.kupreychik.notificationservice.model.entity.UserData;
import com.kupreychik.notificationservice.model.enums.NotificationProvider;
import com.kupreychik.notificationservice.repository.UserDataRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl {

    private final UserDataRepository userDataRepository;

    @PostConstruct
    public void init() {
        userDataRepository.deleteAll();
        userDataRepository.save(UserData.builder()
                .data("764602851")
                .provider(NotificationProvider.TELEGRAM)
                .userId(1L)
                .build());

        userDataRepository.save(UserData.builder()
                .data("emai@email.com")
                .provider(NotificationProvider.MAIL)
                .userId(2L)
                .build());
    }


    public String getDataByUserIdAndProvider(Long userId, NotificationProvider provider) {
        return userDataRepository.findByUserIdAndProvider(userId, provider).getData();
    }
}
