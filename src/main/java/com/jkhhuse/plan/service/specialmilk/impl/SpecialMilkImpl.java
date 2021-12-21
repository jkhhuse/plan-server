package com.jkhhuse.plan.service.specialmilk.impl;

import com.jkhhuse.plan.dao.specialmilk.SpecialMilkDao;
import com.jkhhuse.plan.dto.specialmilk.SpecialMilkDTO;
import com.jkhhuse.plan.entity.specialmilk.SpecialMilkDO;
import com.jkhhuse.plan.service.specialmilk.SpecialMilkService;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpecialMilkImpl implements SpecialMilkService {

    @Resource
    private SpecialMilkDao specialMilkDao;

    @Override
    public SpecialMilkDTO findSpecialMilk(Integer type) {
        SpecialMilkDO specialMilkDO = specialMilkDao.findByType(type);
        DozerBeanMapper mapper = new DozerBeanMapper();
        SpecialMilkDTO specialMilkDTO = mapper.map(specialMilkDO, SpecialMilkDTO.class);
        return specialMilkDTO;
    }
}
