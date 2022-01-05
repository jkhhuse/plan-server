package com.jkhhuse.plan.dao.corporeality;

import com.jkhhuse.plan.entity.corporeality.CorporealityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CorporealityDao extends JpaRepository<CorporealityDO, Long>  {

    List<CorporealityDO> findByPersonUuidAndHeightNotNull(String personUuid);

    List<CorporealityDO> findByPersonUuidAndWeightNotNull(String personUuid);

    @Query(value="select weight from corporeality where person_uuid = ?1 order by record_time desc limit 1", nativeQuery = true)
    Float findLatestWeight(String personUuid);

    /**
     * 获得是否存在相同日期的数据
     * @param RecordTime
     * @param personUuid
     * @return
     */
    Optional<CorporealityDO> findByRecordTimeAndPersonUuid(Date RecordTime, String personUuid);
}
