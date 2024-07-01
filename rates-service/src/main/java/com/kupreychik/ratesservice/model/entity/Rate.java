package com.kupreychik.ratesservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Rates")
public class Rate implements Serializable {

    @Indexed
    private String id;
    @Indexed
    @Getter
    private String currencyFrom;
    @Indexed
    @Getter
    private String currencyTo;
    @Getter
    private BigDecimal amount;
    @Indexed
    @Getter
    private LocalDate localDate;

    public Rate(String currencyFrom, String currencyTo, BigDecimal amount) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.localDate = LocalDate.now();
    }
}
