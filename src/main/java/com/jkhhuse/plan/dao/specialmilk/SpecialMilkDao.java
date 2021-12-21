package com.jkhhuse.plan.dao.specialmilk;

import com.jkhhuse.plan.entity.specialmilk.SpecialMilkDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialMilkDao extends JpaRepository<SpecialMilkDO, Long> {

    SpecialMilkDO findByType(Integer type);
}