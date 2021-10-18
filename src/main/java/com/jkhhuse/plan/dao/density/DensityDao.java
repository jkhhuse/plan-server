package com.jkhhuse.plan.dao.density;

import com.jkhhuse.plan.entity.density.DensityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DensityDao extends JpaRepository<DensityDO, Long> {

	DensityDO findByUuid(long uuid);

//	@Modifying
//	@Transactional(rollbackFor = RuntimeException.class)
//	@Query("delete from density where density_uuid = ?1")
	void deleteByDensity_UuId(String density_uuid);

}