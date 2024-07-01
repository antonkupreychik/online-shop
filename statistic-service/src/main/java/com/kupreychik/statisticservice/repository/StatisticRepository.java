package com.kupreychik.statisticservice.repository;

import com.kupreychik.statisticservice.model.entity.Statistic;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "statistics", path = "statistics")
public interface StatisticRepository extends PagingAndSortingRepository<Statistic, Long> {

    void save(Statistic statistic);

    List<Statistic> findAll();

    List<Statistic> findByTimestampBetween(LocalDateTime from, LocalDateTime to);
}