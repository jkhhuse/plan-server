package com.jkhhuse.plan.service.diet.impl;

import com.jkhhuse.plan.entity.diet.DietDO;
import com.jkhhuse.plan.dao.diet.DietDao;
import com.jkhhuse.plan.dto.diet.DietDTO;
import com.jkhhuse.plan.service.diet.DietService;
import com.jkhhuse.plan.vo.diet.DietVO;
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

    @Override
    public String addDiet(String userId, DietDTO dietDTO) throws ParseException {
        DietDO dietDO = new DietDO();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dietDO.setDietTime(format.parse(dietDTO.getDietTime()));
        dietDO.setDietType(dietDTO.getDietType());
        dietDO.setPersonUuid(userId);
        dietDO.setPheValue(dietDTO.getPheValue());
        dietDO.setDietContent(dietDTO.getDietContent());
        DietDO result = dietDao.save(dietDO);
        return result.getUuid();
    }

    @Override
    public String updateDiet(String dietId, DietDTO dietDTO) throws ParseException {
        DietDO dietDO = dietDao.findByUuid(dietId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dietDO.setDietTime(format.parse(dietDTO.getDietTime()));
        dietDO.setDietType(dietDO.getDietType());
        dietDO.setPheValue(dietDTO.getPheValue());
        dietDO.setDietContent(dietDTO.getDietContent());
        dietDao.save(dietDO);
        return dietDO.getUuid();
    }

    @Override
    public void deleteDiet(String dietId) {
        dietDao.deleteByUuid(dietId);
    }

    @Override
    public List<DietVO> findFixedDateDiets(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<DietDO> results = dietDao.findAllByDietTimeBetween(formatter.parse(date + " 00:00:00"), formatter.parse(date + " 24:00:00"));
        List<DietVO> list = new ArrayList();
        Iterator<DietDO> it = results.iterator();
        while (it.hasNext()) {
            DietDO dietDO = it.next();
            DietVO dietVO = new DietVO();
            dietVO.setUuid(dietDO.getUuid());
            dietVO.setDietTime(formatter.format(dietDO.getDietTime()));
            dietVO.setDietType(dietDO.getDietType());
            dietVO.setPheValue(dietDO.getPheValue());
            dietVO.setDietContent(dietDO.getDietContent());
            list.add(dietVO);
        }
        return list;
    }
}
