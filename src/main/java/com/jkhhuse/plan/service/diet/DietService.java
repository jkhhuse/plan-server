package com.jkhhuse.plan.service.diet;

import com.jkhhuse.plan.dto.diet.DietDTO;

import java.text.ParseException;
import java.util.List;

public interface DietService {

    String addDiet(String userId, DietDTO dietDTO) throws ParseException;

    String updateDiet(String dietId, DietDTO dietDTO) throws ParseException;

    void deleteDiet(String dietId);

    List<DietDTO> findFixedDateDiets(String userId, String date) throws ParseException;

    DietDTO findDietById(String dietId);

    List<DietDTO> findDietsByRange(String userId, String startDate, String endDate) throws ParseException;
}
