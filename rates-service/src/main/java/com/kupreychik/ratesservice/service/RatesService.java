package com.kupreychik.ratesservice.service;

import com.kupreychik.ratesservice.model.entity.Currency;
import com.kupreychik.ratesservice.model.entity.Rate;

import java.time.LocalDate;
import java.util.List;

public interface RatesService {

    List<Rate> getRates(LocalDate now);

    List<Rate> getRates(LocalDate date, String currencyFrom, String currencyTo);

    List<Currency> getCurrencies();
}
