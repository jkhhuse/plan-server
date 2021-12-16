package com.jkhhuse.plan.service.diet;

import com.jkhhuse.plan.dto.diet.DietDTO;
import com.jkhhuse.plan.vo.diet.DietVO;

import java.text.ParseException;
import java.util.List;

public interface DietService {

    String addDiet(String userId, DietDTO dietDTO) throws ParseException;

    String updateDiet(String dietId, DietDTO dietDTO) throws ParseException;

    void deleteDiet(String dietId);

    List<DietDTO> findFixedDateDiets(String date) throws ParseException;

    DietDTO findDietById(String dietId);
}
