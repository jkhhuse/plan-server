package com.jkhhuse.plan.service.density.impl;

import com.jkhhuse.plan.dao.density.DensityDao;
import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.dto.density.DensityDimensionDTO;
import com.jkhhuse.plan.entity.density.DensityDO;
import com.jkhhuse.plan.service.density.DensityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DensityServiceImpl implements DensityService {
    @Resource
    private DensityDao densityDao;

    @Override
    public String addDensity(String userId, DensityDTO densityDTO) throws ParseException {
        DensityDO densityDO = new DensityDO();
        densityDO.setPersonUuid(userId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        densityDO.setMeasureTime(format.parse(densityDTO.getMeasureTime()));
        densityDO.setMeasureValue(densityDTO.getMeasureValue());
        DensityDO result = densityDao.save(densityDO);
        return result.getUuid();
    }

    @Override
    public Boolean deleteDensity(String densityUuid) {
        Boolean isExist = densityDao.existsByUuid(densityUuid);
        if (!isExist) {
            return false;
        }
        densityDao.deleteByUuid(densityUuid);
        return true;
    }

    @Override
    public Boolean countMeasureDuplicate(String measureTime) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int count = densityDao.countAllByMeasureTime(formatter.parse(measureTime));
        return count == 0 ? false : true;
    }

    @Override
    public List<DensityDimensionDTO> getDensitySet(String startTime, String endTime) {
        List<DensityDO> results = null;
        List<DensityDimensionDTO> dList = new ArrayList<>();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            results = densityDao.findByMeasureTimeBetween(formater.parse(startTime), formater.parse(endTime));
            Iterator<DensityDO> it = results.listIterator();
            while (it.hasNext()) {
                DensityDimensionDTO dto = new DensityDimensionDTO();
                DensityDO densityDO = it.next();
                dto.setMeasureTime(formater.format(densityDO.getMeasureTime()));
                dto.setMeasureValue(densityDO.getMeasureValue());
                dList.add(dto);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dList;
    }

    @Override
    public String updateDensity(DensityDTO densityDTO, String densityUuid) throws ParseException {
        DensityDO densityDO = densityDao.findByUuid(densityUuid);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        densityDO.setMeasureTime(formatter.parse(densityDTO.getMeasureTime()));
        densityDO.setMeasureValue(densityDTO.getMeasureValue());
        DensityDO result = densityDao.save(densityDO);
        return result.getUuid();
    }

    @Override
    public List<DensityDimensionDTO> getAllDensity(String userId) {
        List<DensityDO> list = densityDao.findAll();
        List<DensityDimensionDTO> result = new ArrayList<>();
        for (DensityDO densityDO : list) {
            DensityDimensionDTO temp = new DensityDimensionDTO();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            temp.setMeasureTime(formatter.format(densityDO.getMeasureTime()));
            temp.setMeasureValue(densityDO.getMeasureValue());
            result.add(temp);
        }
        return result;
    }

}
