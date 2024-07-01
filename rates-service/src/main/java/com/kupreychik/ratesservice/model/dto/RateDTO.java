package com.kupreychik.ratesservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kupreychik.ratesservice.model.entity.Rate;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RateDTO implements Serializable {
    @JsonProperty("USD_in")
    public String usdIn;
    @JsonProperty("USD_out")
    public String usdOut;
    @JsonProperty("EUR_in")
    public String eurIn;
    @JsonProperty("EUR_out")
    public String eurOut;
    @JsonProperty("RUB_in")
    public String rubIn;
    @JsonProperty("RUB_out")
    public String rubOut;
    @JsonProperty("GBP_in")
    public String gbpIn;
    @JsonProperty("GBP_out")
    public String gbpOut;
    @JsonProperty("CAD_in")
    public String cadIn;
    @JsonProperty("CAD_out")
    public String cadOut;
    @JsonProperty("PLN_in")
    public String plnIn;
    @JsonProperty("PLN_out")
    public String plnOut;
    @JsonProperty("SEK_in")
    public String sekIn;
    @JsonProperty("SEK_out")
    public String sekOut;
    @JsonProperty("CHF_in")
    public String chfIn;
    @JsonProperty("CHF_out")
    public String chfOut;
    @JsonProperty("USD_EUR_in")
    public String usdEurIn;
    @JsonProperty("USD_EUR_out")
    public String usdEurOut;
    @JsonProperty("USD_RUB_in")
    public String usdRubIn;
    @JsonProperty("USD_RUB_out")
    public String usdRubOut;
    @JsonProperty("RUB_EUR_in")
    public String rubEurIn;
    @JsonProperty("RUB_EUR_out")
    public String rubEurOut;
    @JsonProperty("JPY_in")
    public String jpyIn;
    @JsonProperty("JPY_out")
    public String jpyOut;
    @JsonProperty("CNY_in")
    public String cnyIn;
    @JsonProperty("CNY_out")
    public String cnyOut;
    @JsonProperty("CNY_EUR_in")
    public String cnyEurIn;
    @JsonProperty("CNY_EUR_out")
    public String cnyEurOut;
    @JsonProperty("CNY_USD_in")
    public String cnyUsdIn;
    @JsonProperty("CNY_USD_out")
    public String cnyUsdOut;
    @JsonProperty("CNY_RUB_in")
    public String cnyRubIn;
    @JsonProperty("CNY_RUB_out")
    public String cnyRubOut;
    @JsonProperty("CZK_in")
    public String czkIn;
    @JsonProperty("CZK_out")
    public String czkOut;
    @JsonProperty("NOK_in")
    public String nokIn;
    @JsonProperty("NOK_out")
    public String nokOut;


    public Set<String> getCurrencies() {
        Set<String> currencies = new HashSet<>();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            JsonProperty annotation = field.getAnnotation(JsonProperty.class);
            if (annotation != null) {
                String[] parts = annotation.value().split("_");
                if (parts.length > 1) {
                    currencies.add(parts[0]);
                }
            }
        }

        return currencies;
    }

    public List<Rate> getCurrencyRelations() {
        List<Rate> currencyRelations = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        String baseCurrency = "BYN"; // базовая валюта

        for (Field field : fields) {
            JsonProperty annotation = field.getAnnotation(JsonProperty.class);
            if (annotation != null) {
                String[] parts = annotation.value().split("_");
                if (parts.length == 2) {
                    String currency = parts[0];
                    String direction = parts[1];
                    BigDecimal amount = new BigDecimal(getFieldValue(field));

                    if (direction.equals("in")) {
                        currencyRelations.add(new Rate(currency, baseCurrency, amount));
                    } else if (direction.equals("out")) {
                        currencyRelations.add(new Rate(baseCurrency, currency, amount));
                    }
                }
            }
        }
        return currencyRelations;
    }

    private String getFieldValue(Field field) {
        try {
            field.setAccessible(true);
            return (String) field.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
