package com.jkhhuse.plan.service.density;

import com.jkhhuse.plan.dto.density.DensityDTO;

import java.text.ParseException;

public interface DensityService {

    /**
     * 新增用户信息
     *
     * @param densityDTO
     * @return
     */
    String addDensity(DensityDTO densityDTO, String person_uuid) throws ParseException;

    void deleteDensity(String density_uuid);
}
