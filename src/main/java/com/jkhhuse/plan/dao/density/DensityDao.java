package com.jkhhuse.plan.dao.density;

import com.jkhhuse.plan.entity.density.DensityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface DensityDao extends JpaRepository<DensityDO, Long> {

	DensityDO findByUuid(long uuid);

	void deleteByUuid(String uuid);

	/**
	 * 找到测量时间的范围
	 * @param start 初始时间
	 * @param end 结束时间
	 * @return
	 */
	List<DensityDO> findByMeasureTimeBetween(Date start, Date end);
}