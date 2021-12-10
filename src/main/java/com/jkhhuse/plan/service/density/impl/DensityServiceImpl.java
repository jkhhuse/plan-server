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
        densityDao.save(densityDO);
        return null;
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
    public int countMeasureDuplicate(String startTime, String endTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<DensityDO> results = null;
        try {
            results = densityDao.findByMeasureTimeBetween(format.parse(startTime), format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return results.size();
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
    public String updateDensity(DensityDTO densityDTO, String densityId) throws ParseException {
        DensityDO densityDO = densityDao.findByUuid(densityId);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        densityDO.setMeasureTime(formater.parse(densityDTO.getMeasureTime()));
        densityDO.setMeasureValue(densityDTO.getMeasureValue());
        densityDao.save(densityDO);
        return "更新成功";
    }

}
