package com.jkhhuse.plan.service.specialmilk.impl;

import com.jkhhuse.plan.dao.specialmilk.SpecialMilkDao;
import com.jkhhuse.plan.dto.specialmilk.SpecialMilkDTO;
import com.jkhhuse.plan.entity.specialmilk.SpecialMilkDO;
import com.jkhhuse.plan.service.specialmilk.SpecialMilk;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpecialMilkImpl implements SpecialMilk {

    @Resource
    private SpecialMilkDao specialMilkDao;

    @Override
    public SpecialMilkDTO findSpecialMilk(String type) {
        SpecialMilkDO specialMilkDO = specialMilkDao.findByType(type);

        return null;
    }
}
