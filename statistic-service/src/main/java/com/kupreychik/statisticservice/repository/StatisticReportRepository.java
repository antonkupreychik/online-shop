package com.kupreychik.statisticservice.repository;

import com.kupreychik.statisticservice.model.entity.StatisticReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface StatisticReportRepository extends JpaRepository<StatisticReport, Long> {

    StatisticReport findFirstByTimestampGreaterThanOrderByTimestampDesc(LocalDateTime timestamp);
}
