package com.jkhhuse.plan.dao.food;

import com.jkhhuse.plan.entity.person.PersonDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodDao extends JpaRepository<PersonDO, Long> {

}