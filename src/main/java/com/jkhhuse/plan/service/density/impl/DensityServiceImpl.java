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
    public String addDensity(DensityDTO densityDTO, String person_uuid) throws ParseException {
        DensityDO densityDO = new DensityDO();
        densityDO.setPerson_uuid(person_uuid);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        densityDO.setMeasure_time(format.parse(densityDTO.getMeasure_time()));
        densityDO.setMeasure_value(densityDTO.getMeasure_value());
        densityDao.save(densityDO);
        return null;
    }

    @Override
    public void deleteDensity(String density_uuid) {
        densityDao.deleteByDensity_UuId(density_uuid);
    }
}
