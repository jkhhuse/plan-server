package com.jkhhuse.plan.service.diet;

import com.jkhhuse.plan.dto.diet.DietDTO;

import java.text.ParseException;

public interface DietService {

    String addDiet(String userId, DietDTO dietDTO) throws ParseException;

    String updateDiet(String dietId, DietDTO dietDTO) throws ParseException;
}
