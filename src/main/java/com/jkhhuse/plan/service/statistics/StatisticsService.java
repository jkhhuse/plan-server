package com.jkhhuse.plan.service.statistics;

import com.jkhhuse.plan.dto.statistics.StatisticsDTO;

import java.text.ParseException;
import java.util.List;

public interface StatisticsService {

    /**
     * 获得最近 N Day 的统计数据
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param days 天数
     * @return StatisticsDTO
     */
    StatisticsDTO latestDayStats(String startDate, String endDate, Integer days);

    /**
     * 获得最近 N Day 的统计数据
     * @param measureTime 测量时间
     * @param days 天数
     * @return
     */
    StatisticsDTO searchLatestStats(String measureTime, Integer days) throws ParseException;

    /**
     * 保存信息
     * @param measureTime
     */
    void saveLatestDayStats(String measureTime) throws ParseException;
}
