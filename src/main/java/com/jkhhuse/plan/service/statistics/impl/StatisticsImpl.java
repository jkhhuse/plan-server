package com.jkhhuse.plan.service.statistics.impl;

import com.jkhhuse.plan.dto.diet.DietDTO;
import com.jkhhuse.plan.dto.statistics.StatisticsDTO;
import com.jkhhuse.plan.service.diet.DietService;
import com.jkhhuse.plan.service.specialmilk.SpecialMilkService;
import com.jkhhuse.plan.service.statistics.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StatisticsImpl implements StatisticsService {

    @Resource
    private SpecialMilkService specialMilkService;

    @Resource
    private DietService dietService;

    @Override
    public StatisticsDTO latestDayStats(String startDate, String endDate) {
        // 获得最近 N 天的饮食数据，并拆分出A:特奶量、B:母乳量、C:食物量

        Float specialProteinSum = 0.0F;
        Float natureProteinSum = 0.0F;
        Float totalProteinSum = 0.0F;
        Float pheValueSum = 0.0F;

        List<DietDTO> list = new ArrayList<>();
        try {
            list = dietService.findDietsByRange(startDate, endDate);

            Iterator<DietDTO> it = list.iterator();
            while(it.hasNext()) {
                DietDTO diet = it.next();
                pheValueSum = pheValueSum + diet.getPheValue();
                // 获得特殊奶粉类型，从而计算蛋白质含量
                specialProteinSum = specialProteinSum + diet.getSpecialMilk();
                natureProteinSum = natureProteinSum + diet.getBreastMilk();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // 对 A/B/C 分类计算，B/C ==> 天然，A ==> 特殊

        // 对结果汇总输出
        return null;
    }
}
