package com.kupreychik.ratesservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kupreychik.ratesservice.config.BelarusBankApiProperties;
import com.kupreychik.ratesservice.model.dto.RateDTO;
import com.kupreychik.ratesservice.model.entity.Currency;
import com.kupreychik.ratesservice.model.entity.Rate;
import com.kupreychik.ratesservice.repository.CurrencyRepository;
import com.kupreychik.ratesservice.repository.RatesRepository;
import com.kupreychik.ratesservice.service.RatesService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatesServiceImpl implements RatesService {

    private final CurrencyRepository currencyRepository;
    private final RatesRepository ratesRepository;
    private final ObjectMapper objectMapper;
    private final BelarusBankApiProperties belarusBankApiProperties;


    @SneakyThrows
    @PostConstruct
    public void init() {
        log.info("Trying to init rates for today");

        boolean needToLoadCurrencies = !currencyRepository.findAll().iterator().hasNext();
        boolean needToLoadRates = ratesRepository.findAllByLocalDate(LocalDate.now()).isEmpty();

        if (needToLoadCurrencies || needToLoadRates) {

            RestClient restClient = RestClient.create();
            String rates = restClient.get()
                    .uri(belarusBankApiProperties.getUrl() + belarusBankApiProperties.getPath())
                    .retrieve()
                    .body(String.class);
            List<RateDTO> root = Arrays.stream(objectMapper.readValue(rates, RateDTO[].class))
                    .toList();

            pushCurrencies(needToLoadCurrencies, root);
            pushRates(needToLoadRates, root);
        } else {
            log.info("No currencies and rates need to be loaded");
        }
    }

    @Override
    public List<Rate> getRates(LocalDate date, String currencyFrom, String currencyTo) {
        if (currencyFrom == null && currencyTo != null) {
            return ratesRepository.findAllByLocalDateAndCurrencyTo(date, currencyTo);
        }
        if (currencyFrom != null && currencyTo == null) {
            return ratesRepository.findAllByLocalDateAndCurrencyFrom(date, currencyFrom);
        }
        if (currencyFrom != null & currencyTo != null) {
            return ratesRepository.findAllByLocalDateAndCurrencyToAndCurrencyFrom(date, currencyTo, currencyFrom);
        }
        return getRates(LocalDate.now());
    }

    public List<Rate> getRates(LocalDate now) {
        return ratesRepository.findAllByLocalDate(now);
    }

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    private void pushCurrencies(boolean needToLoadCurrencies, List<RateDTO> root) {
        if (needToLoadCurrencies) {
            log.info("No currencies found");
            root.get(0).getCurrencies()
                    .stream()
                    .map(Currency::new).forEach(currencyRepository::save);
            currencyRepository.save(new Currency("BYN"));
        }
    }

    private void pushRates(boolean needToLoadRates, List<RateDTO> root) {
        if (needToLoadRates) {
            ratesRepository.saveAll(root.get(0).getCurrencyRelations());
            log.info("Rates for {} day succeeded", LocalDate.now());
        }
    }
}
