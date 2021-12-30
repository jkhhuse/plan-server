package com.jkhhuse.plan.service.statistics.impl;

import com.jkhhuse.plan.dto.diet.DietDTO;
import com.jkhhuse.plan.dto.food.FoodDTO;
import com.jkhhuse.plan.dto.specialmilk.SpecialMilkDTO;
import com.jkhhuse.plan.dto.statistics.StatisticsDTO;
import com.jkhhuse.plan.service.diet.DietService;
import com.jkhhuse.plan.service.food.FoodService;
import com.jkhhuse.plan.service.specialmilk.SpecialMilkService;
import com.jkhhuse.plan.service.statistics.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
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

    @Resource
    private FoodService foodService;

    @Override
    public StatisticsDTO latestDayStats(String startDate, String endDate, Integer days) {
        // 获得最近 N 天的饮食数据，并拆分出A:特奶量、B:母乳量、C:食物量

        Float specialProteinSum = 0.0F;
        Float natureProteinSum = 0.0F;
        Float breastProteinSum = 0.0F;
        Float FoodProteinSum = 0.0F;
        Float totalProteinSum = 0.0F;
        Float pheValueSum = 0.0F;

        SpecialMilkDTO specialMilkType0 = specialMilkService.findSpecialMilk(0);

        StatisticsDTO statisticsDTO = new StatisticsDTO();

        List<DietDTO> list = new ArrayList<>();
        try {
            list = dietService.findDietsByRange(startDate, endDate);

            Iterator<DietDTO> it = list.iterator();
            while(it.hasNext()) {
                DietDTO diet = it.next();
                pheValueSum = pheValueSum + diet.getPheValue();

                DecimalFormat df = new DecimalFormat("#.00");
                Float specialProtein = 0.0F;
                // 根据特奶类型来觉得蛋白质如何计算
                if(diet.getSmilkType() == 0) {
                    String specialProteinStr = df.format(diet.getSpecialMilk() / specialMilkType0.getProtein());
                    specialProtein = Float.parseFloat(specialProteinStr);
                }
                // 获得特殊奶粉类型，从而计算蛋白质含量
                specialProteinSum = specialProteinSum + specialProtein;

                // 母乳蛋白质计算，100ml 1.1g 蛋白质
                String breastProtein = df.format(diet.getBreastMilk() / 100 * 1.1F);
                breastProteinSum = breastProteinSum + Float.parseFloat(breastProtein);

                // 天然食物蛋白质计算
                String foodUuid = diet.getFoodUuid();
                FoodDTO food = foodService.findFood(foodUuid);
                // TODO: protein 为 -（空）的情况
                Float foodProtein = Float.parseFloat(food.getProtein());
                FoodProteinSum = FoodProteinSum + foodProtein * (diet.getFoodAmount() / 100);
            }

            statisticsDTO.setTotalProtein(0F);
            statisticsDTO.setPheValue(pheValueSum / days);
            statisticsDTO.setNatureProtein(natureProteinSum / days);
            statisticsDTO.setSpecialProtein(specialProteinSum / days);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        // 对 A/B/C 分类计算，B/C ==> 天然，A ==> 特殊

        // 对结果汇总输出
        return null;
    }
}
