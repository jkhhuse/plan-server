package com.jkhhuse.plan.dao.density;

import com.jkhhuse.plan.entity.density.DensityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface DensityDao extends JpaRepository<DensityDO, Long> {

    DensityDO findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    int countAllByMeasureTimeAndPersonUuid(String personUuid, Date measureTime);

    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    void deleteByUuid(String uuid);

    /**
     * 找到测量时间的范围
     *
     * @param start 初始时间
     * @param end   结束时间
     * @return
     */
    List<DensityDO> findByPersonUuidAndMeasureTimeBetween(String personUuid, Date start, Date end);

    /**
     * 查询全部并且倒序排序
     * @return
     */
    List<DensityDO> findAllByPersonUuidOrderByMeasureTimeDesc(String personUuid);

    @Query(value = "select * from density where person_uuid =:userId order by measure_time desc limit :count", nativeQuery = true)
    List<DensityDO> findTopNByPersonUuid(String userId, Integer count);

    @Query(value="select count(uuid) from density where person_uuid =?1 and measure_value < 2", nativeQuery = true)
    Integer countLow(String userId);

    @Query(value="select count(uuid) from density where person_uuid =?1 and measure_value >=2 and measure_value <= 4", nativeQuery = true)
    Integer countNormal(String userId);

    @Query(value="select count(uuid) from density where person_uuid =?1 and measure_value >4 and measure_value <= 6", nativeQuery = true)
    Integer countNotice(String userId);

    @Query(value="select count(uuid) from density where person_uuid =?1 and measure_value >6 and measure_value <= 10", nativeQuery = true)
    Integer countHigh(String userId);

    @Query(value="select count(uuid) from density where person_uuid =?1 and measure_value >10", nativeQuery = true)
    Integer countDanger(String userId);

    @Query(value="select measureTime from density where person_uuid =?1 order by measure_time desc limit 1", nativeQuery = true)
    Date getLatestMeasureTime(String userId);

}