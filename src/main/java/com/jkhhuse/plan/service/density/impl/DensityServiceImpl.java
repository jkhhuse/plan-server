package com.jkhhuse.plan.service.density.impl;

import com.jkhhuse.plan.dao.density.DensityDao;
import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.dto.density.DensityDimensionDTO;
import com.jkhhuse.plan.dto.density.DensityScaleDTO;
import com.jkhhuse.plan.entity.density.DensityDO;
import com.jkhhuse.plan.service.density.DensityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class DensityServiceImpl implements DensityService {
    @Resource
    private DensityDao densityDao;

    private List<DensityDTO> convertDTO(List<DensityDO> list) {
        List<DensityDTO> result = new ArrayList<>();
        for (DensityDO densityDO : list) {
            DensityDTO temp = new DensityDTO();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            temp.setUuid(densityDO.getUuid());
            temp.setMeasureTime(formatter.format(densityDO.getMeasureTime()));
            temp.setMeasureValue(densityDO.getMeasureValue());
            result.add(temp);
        }
        return result;
    }

    /**
     * 装在 Scale 对象
     *
     * @param name
     * @param value
     * @return
     */
    private DensityScaleDTO loadDensityScaleDTO(String name, int value) {
        DensityScaleDTO scale = new DensityScaleDTO();
        scale.setName(name);
        scale.setValue(value);
        return scale;
    }

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
    public Boolean countMeasureDuplicate(String userId, String measureTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int count = densityDao.countAllByMeasureTimeAndPersonUuid(userId, formatter.parse(measureTime));
        return count == 0 ? false : true;
    }

    @Override
    public List<DensityDimensionDTO> getDensitySet(String userId, String startTime, String endTime) {
        List<DensityDO> results = null;
        List<DensityDimensionDTO> dList = new ArrayList<>();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            results = densityDao.findByPersonUuidAndMeasureTimeBetween(userId, formater.parse(startTime), formater.parse(endTime));
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
        densityDO.setPersonUuid(densityDO.getPersonUuid());
        DensityDO result = densityDao.save(densityDO);
        return result.getUuid();
    }

    @Override
    public List<DensityDTO> getAllDensity(String userId) {
        List<DensityDO> list = densityDao.findAllByPersonUuidOrderByMeasureTimeDesc(userId);
        return convertDTO(list);
    }

    @Override
    public List<DensityDTO> getTopDensity(String userId, Integer count) {
        List<DensityDO> list = densityDao.findTopNByPersonUuid(userId, count);
        return convertDTO(list);
    }

    @Override
    public List<DensityScaleDTO> countRangeDensity(String userId) {
        Integer low = densityDao.countLow(userId);
        Integer normal = densityDao.countNormal(userId);
        Integer notice = densityDao.countNotice(userId);
        int high = densityDao.countHigh(userId);
        Integer danger = densityDao.countDanger(userId);
        List<DensityScaleDTO> densityScaleDTO = new ArrayList<>();
        densityScaleDTO.add(loadDensityScaleDTO("0-2 mg/dl", low));
        densityScaleDTO.add(loadDensityScaleDTO("2-4 mg/dl", normal));
        densityScaleDTO.add(loadDensityScaleDTO("4-6 mg/dl", notice));
        densityScaleDTO.add(loadDensityScaleDTO("6-10 mg/dl", high));
        densityScaleDTO.add(loadDensityScaleDTO("10+ mg/dl", danger));
        return densityScaleDTO;
    }

    @Override
    public Date getLatestMeasureTime(String userId) {
        Date measureTime = densityDao.getLatestMeasureTime(userId);
        return measureTime;
    }

}
