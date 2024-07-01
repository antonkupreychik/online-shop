package com.kupreychik.ratesservice.repository;

import com.kupreychik.ratesservice.model.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    List<Currency> findAll();
}
