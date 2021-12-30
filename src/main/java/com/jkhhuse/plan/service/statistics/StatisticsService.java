package com.jkhhuse.plan.service.statistics;

import com.jkhhuse.plan.dto.statistics.StatisticsDTO;

public interface StatisticsService {

    /**
     * 获得最近 N Day 的统计数据
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param days 天数
     * @return StatisticsDTO
     */
    StatisticsDTO latestDayStats(String startDate, String endDate, Integer days);
}
