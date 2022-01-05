package com.jkhhuse.plan.service.corporeality.impl;

import com.jkhhuse.plan.dao.corporeality.CorporealityDao;
import com.jkhhuse.plan.dto.corporeality.CorporealityDTO;
import com.jkhhuse.plan.entity.corporeality.CorporealityDO;
import com.jkhhuse.plan.service.corporeality.CorporealityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CorporealityServiceImpl implements CorporealityService {

    @Resource
    private CorporealityDao corporealityDao;

    /**
     * 转换 CorporealityDO -> CorporealityDTO list
     * @param source CorporealityDO
     * @return
     */
    private List<CorporealityDTO> convert(List<CorporealityDO> source) {
        List<CorporealityDTO> target = new ArrayList<>();
        Iterator<CorporealityDO> it = source.iterator();
        while (it.hasNext()) {
            CorporealityDO corporealityDO = it.next();
            CorporealityDTO corporealityDTO = new CorporealityDTO();
            corporealityDTO.setUuid(corporealityDO.getUuid());
            corporealityDTO.setHeight(corporealityDO.getHeight());
            corporealityDTO.setWeight(corporealityDO.getWeight());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String recordTime = formatter.format(corporealityDO.getRecordTime());
            corporealityDTO.setRecordTime(recordTime);
            target.add(corporealityDTO);
        }
        return target;
    }

    @Override
    public String addCorporeality(String personUuid, CorporealityDTO corporealityDTO) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date recordTime = formatter.parse(corporealityDTO.getRecordTime());

        Optional<CorporealityDO> cRecord = corporealityDao.findByRecordTimeAndPersonUuid(recordTime, personUuid);

        CorporealityDO corporealityDO ;

        if (!cRecord.isPresent()) {
            corporealityDO = new CorporealityDO();
        } else {
            corporealityDO = cRecord.get();
        }

        if (corporealityDTO.getHeight() == 0.0F && corporealityDTO.getWeight() != 0.0F) {
            corporealityDO.setWeight(corporealityDTO.getWeight());
        } else if (corporealityDTO.getHeight() != 0.0F && corporealityDTO.getWeight() == 0.0F) {
            corporealityDO.setHeight(corporealityDTO.getHeight());
        } else if (corporealityDTO.getHeight() != 0.0F && corporealityDTO.getWeight() != 0.0F) {
            corporealityDO.setWeight(corporealityDTO.getWeight());
            corporealityDO.setHeight(corporealityDTO.getHeight());
        }

        corporealityDO.setPersonUuid(personUuid);
        corporealityDO.setRecordTime(formatter.parse(corporealityDTO.getRecordTime()));
        CorporealityDO result = corporealityDao.save(corporealityDO);
        return result.getUuid();
    }

    @Override
    public List<CorporealityDTO> getHeightVisual(String personUuid) {
        List<CorporealityDO> list = corporealityDao.findByPersonUuidAndHeightNotNull(personUuid);
        return convert(list);
    }

    @Override
    public List<CorporealityDTO> getWeightVisual(String personUuid) {
        List<CorporealityDO> list = corporealityDao.findByPersonUuidAndWeightNotNull(personUuid);
        return convert(list);
    }

    @Override
    public Float getLatestWeight(String personUuid) {
        Float weight = corporealityDao.findLatestWeight(personUuid);
        return weight;
    }
}
