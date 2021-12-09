package com.jkhhuse.plan.dao.diet;

import com.jkhhuse.plan.entity.person.PersonDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietDao extends JpaRepository<DietDO, Long> {

    PersonDO findByUuid(String uuid);
}