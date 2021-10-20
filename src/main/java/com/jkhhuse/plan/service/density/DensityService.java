package com.jkhhuse.plan.service.density;

import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.dto.density.DensityDimensionDTO;

import java.text.ParseException;
import java.util.List;

public interface DensityService {

    /**
     * 新增用户信息
     *
     * @param densityDTO
     * @return
     */
    String addDensity(DensityDTO densityDTO) throws ParseException;

    void deleteDensity(String densityUuid);

    int countMeasureDuplicate(String startTime, String endTime);

    List<DensityDimensionDTO> getDensitySet(String startTime, String endTime);
}
