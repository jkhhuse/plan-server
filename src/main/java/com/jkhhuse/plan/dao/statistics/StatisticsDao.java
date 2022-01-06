package com.jkhhuse.plan.dao.statistics;

import com.jkhhuse.plan.entity.statistics.StatisticsDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface StatisticsDao extends JpaRepository<StatisticsDO, Long> {

    Optional<StatisticsDO> findByMeasureTimeAndDaysAndPersonUuid(String personUuid, Date measureTime, Integer days);

    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    void deleteByMeasureTimeAndPersonUuid(String personUuid, Date measureTime);
}
