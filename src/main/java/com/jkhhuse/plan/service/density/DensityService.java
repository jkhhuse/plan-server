package com.jkhhuse.plan.service.density;

import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.dto.density.DensityDimensionDTO;
import com.jkhhuse.plan.dto.density.DensityScaleDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface DensityService {

    /**
     * 新增用户血值信息
     * @param userId
     * @param densityDTO
     * @return
     */
    String addDensity(String userId, DensityDTO densityDTO) throws ParseException;

    Boolean deleteDensity(String densityUuid);

    Boolean countMeasureDuplicate(String userId, String measureTime) throws ParseException;

    List<DensityDimensionDTO> getDensitySet(String userId, String startTime, String endTime);

    String updateDensity(DensityDTO densityDTO, String densityUuid) throws ParseException;

    List<DensityDTO> getAllDensity(String userId);

    List<DensityDTO> getTopDensity(String userId, Integer count);

    List<DensityScaleDTO> countRangeDensity(String userId);

    Date getLatestMeasureTime(String userId);
}
