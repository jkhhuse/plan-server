package com.jkhhuse.plan.service.density;

import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.entity.density.DensityDO;

import java.text.ParseException;

public interface DensityService {

    /**
     * 新增用户信息
     * @param densityDTO
     * @return
     */
    String addDensity(DensityDTO densityDTO, String person_uuid) throws ParseException;

    /**
     * 更新用户信息
     * @param uuid
     * @param lastName
     * @return
     */
    DensityDO updateDensity(long uuid, String lastName);

}
