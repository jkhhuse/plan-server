package com.jkhhuse.plan.dao.diet;

import com.jkhhuse.plan.entity.diet.DietDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DietDao extends JpaRepository<DietDO, Long> {

    DietDO findByUuid(String uuid);

    void deleteByUuid(String uuid);

    List<DietDO> findAllByDietTimeBetween(Date startTime, Date endTime);
}