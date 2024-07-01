package com.kupreychik.ratesservice.repository;

import com.kupreychik.ratesservice.model.entity.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RatesRepository extends CrudRepository<Rate, Long> {

    List<Rate> findAllByLocalDateAndCurrencyFrom(LocalDate date, String currency);

    List<Rate> findAllByLocalDateAndCurrencyTo(LocalDate date, String currency);

    List<Rate> findAllByLocalDateAndCurrencyToAndCurrencyFrom(LocalDate date, String currencyTo, String currencyFrom);

    List<Rate> findAllByLocalDate(LocalDate now);
}
