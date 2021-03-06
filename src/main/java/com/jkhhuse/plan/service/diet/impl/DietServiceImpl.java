package com.jkhhuse.plan.service.diet.impl;

import com.jkhhuse.plan.entity.diet.DietDO;
import com.jkhhuse.plan.dao.diet.DietDao;
import com.jkhhuse.plan.dto.diet.DietDTO;
import com.jkhhuse.plan.enumration.diet.DietTypeEnum;
import com.jkhhuse.plan.service.diet.DietService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DietServiceImpl implements DietService {

    @Resource
    private DietDao dietDao;

    private DietDTO convertToDietDTO(DietDO dietDO) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DietDTO dietDTO = new DietDTO();
        dietDTO.setUuid(dietDO.getUuid());
        dietDTO.setDietTime(formatter.format(dietDO.getDietTime()));
        dietDTO.setDietType(dietDO.getDietType());
        dietDTO.setPheValue(dietDO.getPheValue());
        dietDTO.setDietContent(dietDO.getDietContent());
        if (dietDO.getDietType().intValue() == DietTypeEnum.SPECIAL_MILK.getIndex()) {
            dietDTO.setSpecialMilk(dietDO.getSpecialMilk());
            dietDTO.setSmilkType(dietDO.getSmilkType());
        } else if (dietDO.getDietType().intValue() == DietTypeEnum.BREAST_MILK.getIndex()) {
            dietDTO.setBreastMilk(dietDO.getBreastMilk());
        } else {
            dietDTO.setFoodAmount(dietDO.getFoodAmount());
            dietDTO.setFoodUuid(dietDO.getFoodUuid());
            dietDTO.setFoodName(dietDO.getFoodName());
        }
        return dietDTO;
    }

    private List<DietDTO> getDietList(List<DietDO> results) {
        List<DietDTO> list = new ArrayList();
        Iterator<DietDO> it = results.iterator();
        while (it.hasNext()) {
            DietDO dietDO = it.next();
            list.add(convertToDietDTO(dietDO));
        }
        return list;
    }

    private DietDO composeDietSave(DietDO dietDO, DietDTO dietDTO) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dietDO.setDietTime(format.parse(dietDTO.getDietTime()));
        dietDO.setDietType(dietDO.getDietType());
        dietDO.setPheValue(dietDTO.getPheValue());
        dietDO.setDietContent(dietDTO.getDietContent());
        if (dietDTO.getDietType().intValue() == DietTypeEnum.SPECIAL_MILK.getIndex()) {
            dietDO.setSpecialMilk(dietDTO.getSpecialMilk());
            dietDO.setSmilkType(dietDTO.getSmilkType());
        } else if (dietDTO.getDietType().intValue() == DietTypeEnum.BREAST_MILK.getIndex()) {
            dietDO.setBreastMilk(dietDTO.getBreastMilk());
        } else {
            dietDO.setFoodAmount(dietDTO.getFoodAmount());
            dietDO.setFoodUuid(dietDTO.getFoodUuid());
            dietDO.setFoodName(dietDTO.getFoodName());
        }
        return dietDO;
    }

    @Override
    public String addDiet(String userId, DietDTO dietDTO) throws ParseException {
        DietDO dietDO = new DietDO();
        dietDO.setDietType(dietDTO.getDietType());
        dietDO.setPersonUuid(userId);
        DietDO result = dietDao.save(composeDietSave(dietDO, dietDTO));
        return result.getUuid();
    }

    @Override
    public String updateDiet(String dietId, DietDTO dietDTO) throws ParseException {
        DietDO dietDO = dietDao.findByUuid(dietId);
        dietDao.save(composeDietSave(dietDO, dietDTO));
        return dietDO.getUuid();
    }

    @Override
    public void deleteDiet(String dietId) {
        dietDao.deleteByUuid(dietId);
    }

    @Override
    public List<DietDTO> findFixedDateDiets(String userId, String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<DietDO> results = dietDao.findAllByPersonUuidAndDietTimeBetween(userId, formatter.parse(date + " 00:00:00"), formatter.parse(date + " 23:59:59"));
        return getDietList(results);
    }

    @Override
    public DietDTO findDietById(String dietId) {
        DietDO dietDO = dietDao.findByUuid(dietId);
        return convertToDietDTO(dietDO);
    }

    @Override
    public List<DietDTO> findDietsByRange(String userId, String startDate, String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<DietDO> results = dietDao.findAllByPersonUuidAndDietTimeBetween(userId, formatter.parse(startDate + " 00:00:00"), formatter.parse(endDate + " 00:00:00"));
        return getDietList(results);
    }

}