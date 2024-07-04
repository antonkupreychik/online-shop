package com.kupreychik.notificationservice.repository;

import com.kupreychik.notificationservice.model.entity.UserData;
import com.kupreychik.notificationservice.model.enums.NotificationProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends MongoRepository<UserData, String> {
    UserData findByUserIdAndProvider(Long userId, NotificationProvider notificationProvider);
}
