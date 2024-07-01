package com.kupreychik.statisticservice.repository;

import com.kupreychik.statisticservice.model.Statistic;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "statistics", path = "statistics")
public interface StatRepository extends PagingAndSortingRepository<Statistic, Long> {

    void save(Statistic statistic);

    List<Statistic> findAll();
}