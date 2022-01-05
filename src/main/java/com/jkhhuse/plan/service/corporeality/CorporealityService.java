package com.jkhhuse.plan.service.corporeality;

import com.jkhhuse.plan.dto.corporeality.CorporealityDTO;

import java.text.ParseException;
import java.util.List;

public interface CorporealityService {

    String addCorporeality(String personUuid, CorporealityDTO corporealityDTO) throws ParseException;

    List<CorporealityDTO> getHeightVisual(String personUuid);

    List<CorporealityDTO> getWeightVisual(String personUuid);

    Float getLatestWeight(String personUuid);
}
