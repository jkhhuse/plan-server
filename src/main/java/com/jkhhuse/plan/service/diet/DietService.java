package com.jkhhuse.plan.service.diet;

import com.jkhhuse.plan.dto.diet.DietDTO;

import java.text.ParseException;
import java.util.List;

public interface DietService {

    String addDiet(String userId, DietDTO dietDTO) throws ParseException;

    void updateDiet(String dietId, DietDTO dietDTO) throws ParseException;

    void deleteDiet(String dietId);

    List<DietDTO> findFixedDateDiets(String date);
}
