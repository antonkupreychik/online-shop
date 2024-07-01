package com.kupreychik.ratesservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Currencies")
public class Currency implements Serializable {
    private String id;
    private String code;

    public Currency(String code) {
        this.code = code;
    }
}
