package com.jkhhuse.plan.service.statistics.impl;

import com.jkhhuse.plan.dao.statistics.StatisticsDao;
import com.jkhhuse.plan.dto.diet.DietDTO;
import com.jkhhuse.plan.dto.food.FoodDTO;
import com.jkhhuse.plan.dto.specialmilk.SpecialMilkDTO;
import com.jkhhuse.plan.dto.statistics.StatisticsDTO;
import com.jkhhuse.plan.entity.diet.DietDO;
import com.jkhhuse.plan.entity.statistics.StatisticsDO;
import com.jkhhuse.plan.service.diet.DietService;
import com.jkhhuse.plan.service.food.FoodService;
import com.jkhhuse.plan.service.specialmilk.SpecialMilkService;
import com.jkhhuse.plan.service.statistics.StatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsImpl implements StatisticsService {

    @Resource
    private SpecialMilkService specialMilkService;

    @Resource
    private DietService dietService;

    @Resource
    private FoodService foodService;

    @Resource
    private StatisticsDao statisticsDao;

    @Override
    public StatisticsDTO latestDayStats(String startDate, String endDate, Integer days) {
        // 获得最近 N 天的饮食数据，并拆分出A:特奶量、B:母乳量、C:食物量

        Float specialProteinSum = 0.0F;
        Integer specialProteinMlSum = 0;
        Float natureProteinSum = 0.0F;
        Float breastProteinSum = 0.0F;
        Float FoodProteinSum = 0.0F;
        Float totalProteinSum = 0.0F;
        Float pheValueSum = 0.0F;

        SpecialMilkDTO specialMilkType0 = specialMilkService.findSpecialMilk(0);

        StatisticsDTO statisticsDTO = new StatisticsDTO();

        List<DietDTO> list = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("#.00");

        try {
            list = dietService.findDietsByRange(startDate, endDate);
            Iterator<DietDTO> it = list.listIterator();

            for (DietDTO diet : list) {
                pheValueSum = pheValueSum + diet.getPheValue();

                Float specialProtein = 0.0F;
                // 根据特奶类型来觉得蛋白质如何计算
                // TODO: 其他类型的特奶计算
                if (diet.getSmilkType() != null && diet.getSmilkType() == 0) {
                    String specialProteinStr = df.format(diet.getSpecialMilk() / specialMilkType0.getProtein());
                    specialProtein = Float.parseFloat(specialProteinStr);
                }

                // 获得特殊奶粉类型，从而计算蛋白质含量
                if (diet.getSpecialMilk() != null) {
                    specialProteinSum = specialProteinSum + specialProtein;
                    specialProteinMlSum = specialProteinMlSum + diet.getSpecialMilk();
                }

                // 母乳蛋白质计算，100ml 1.2g 蛋白质
                if (diet.getBreastMilk() != null) {
                    String breastProtein = df.format(diet.getBreastMilk() / 100 * 1.2F);
                    breastProteinSum = breastProteinSum + Float.parseFloat(breastProtein);
                }

                // 天然食物蛋白质计算
                if (diet.getFoodUuid() != null) {
                    String foodUuid = diet.getFoodUuid();
                    FoodDTO food = foodService.findFood(foodUuid);
                    // TODO: protein 为 -（空）的情况
                    Float foodProtein = Float.parseFloat(food.getProtein());
                    FoodProteinSum = FoodProteinSum + foodProtein * (diet.getFoodAmount() / 100);
                }
            }

            // 对 A/B/C 分类计算，B/C ==> 天然，A ==> 特殊
            natureProteinSum = breastProteinSum + FoodProteinSum;
            totalProteinSum = natureProteinSum + specialProteinSum;
            statisticsDTO.setPheValue(Float.parseFloat(df.format(pheValueSum / days)));
            statisticsDTO.setNatureProtein(Float.parseFloat(df.format(natureProteinSum / days)));
            statisticsDTO.setSpecialProtein(Float.parseFloat(df.format(specialProteinSum / days)));
            statisticsDTO.setTotalProtein(Float.parseFloat(df.format(totalProteinSum / days)));
            statisticsDTO.setSpecialProteinMl(Float.parseFloat(df.format(specialProteinMlSum / days)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 对结果汇总输出
        return statisticsDTO;
    }

    @Override
    public StatisticsDTO searchLatestStats(String measureTime, Integer days) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Optional<StatisticsDO> statisticsDO =  statisticsDao.findByMeasureTimeAndDays(formatter.parse(measureTime), days);

        if(Optional.ofNullable(statisticsDO).isEmpty()) {
            return  null;
        } else {
            StatisticsDTO statisticsDTO = new StatisticsDTO();
            BeanUtils.copyProperties(statisticsDO.get(), statisticsDTO);
            return statisticsDTO;
        }
    }

    @Override
    public void saveLatestDayStats(String measureTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // 删除重复的 measureTime 数据
        statisticsDao.deleteByMeasureTime(formatter.parse(measureTime));

        LocalDate endDate = LocalDate.of(Integer.parseInt(measureTime.split("-")[0]), Integer.parseInt(measureTime.split("-")[1]), Integer.parseInt(measureTime.split("-")[2]));
        StatisticsDTO statisticsThreeDayDTO = latestDayStats(endDate.minusDays(3).format(DateTimeFormatter.ISO_LOCAL_DATE), measureTime, 3);
        StatisticsDO threeDayDo = new StatisticsDO();
        BeanUtils.copyProperties(statisticsThreeDayDTO, threeDayDo);
        threeDayDo.setDays(3);
        threeDayDo.setMeasureTime(formatter.parse(measureTime));
        statisticsDao.save(threeDayDo);

        StatisticsDTO statisticsSevenDayDTO = latestDayStats(endDate.minusDays(7).format(DateTimeFormatter.ISO_LOCAL_DATE), measureTime, 7);
        StatisticsDO sevenDayDo = new StatisticsDO();
        BeanUtils.copyProperties(statisticsSevenDayDTO, sevenDayDo);
        sevenDayDo.setDays(7);
        sevenDayDo.setMeasureTime(formatter.parse(measureTime));
        statisticsDao.save(sevenDayDo);
    }

}
