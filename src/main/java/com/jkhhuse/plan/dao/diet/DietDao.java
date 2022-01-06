package com.jkhhuse.plan.dao.diet;

import com.jkhhuse.plan.entity.diet.DietDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface DietDao extends JpaRepository<DietDO, Long> {

    DietDO findByUuid(String uuid);

    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    void deleteByUuid(String uuid);

    List<DietDO> findAllByPersonUuidAndDietTimeBetween(String personUuid, Date startTime, Date endTime);
}