package com.jkhhuse.plan.service.density.impl;

import com.jkhhuse.plan.dao.density.DensityDao;
import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.entity.density.DensityDO;
import com.jkhhuse.plan.service.density.DensityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class DensityServiceImpl implements DensityService {
    @Resource
    private DensityDao densityDao;

    @Override
    public String addDensity(DensityDTO densityDTO, String personUuid) throws ParseException {
        DensityDO densityDO = new DensityDO();
        densityDO.setPersonUuid(personUuid);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        densityDO.setMeasureTime(format.parse(densityDTO.getMeasureTime()));
        densityDO.setMeasureValue(densityDTO.getMeasureValue());
        densityDao.save(densityDO);
        return null;
    }

    @Override
    public void deleteDensity(String density_uuid) {
        densityDao.deleteByUuid(density_uuid);
    }
}
