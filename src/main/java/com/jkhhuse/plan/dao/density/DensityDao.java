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

    int countAllByMeasureTime(Date measureTime);

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
    List<DensityDO> findByMeasureTimeBetween(Date start, Date end);

    /**
     * 查询全部并且倒序排序
     * @return
     */
    List<DensityDO> findAllByOrderByMeasureTimeDesc();

    @Query(value = "select * from density order by measure_time desc limit :count", nativeQuery = true)
    List<DensityDO> findTopN(Integer count);

    @Query(value="select count(uuid) from density where measure_value < 2")
    Long countLow();

//    @Query(value="select count(uuid) from density where measure_value >=2 and measure_value <= 4")
//    Integer countNormal();

    @Query(value="select count(uuid) from density where measure_value >4 and measure_value <= 6")
    Integer countNotice();

//    @Query(value="select count(uuid) from density where measure_value >6 and measure_value <= 10")
//    Integer countHigh();

//    @Query(value="select count(uuid) from density where measure_value >10")
//    Integer countDanger();

}