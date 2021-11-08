package com.jkhhuse.plan.service.person;

import com.jkhhuse.plan.dto.person.PersonDTO;
import com.jkhhuse.plan.entity.person.PersonDO;

import java.text.ParseException;

public interface PersonService {

    PersonDO findPersonById(String uuid);

    PersonDO findPersonByName(String name);

    /**
     * 新增用户信息
     * @param personDTO
     * @return
     */
    String addPerson(PersonDTO personDTO) throws ParseException;

    /**
     * 更新用户信息
     * @param uuid
     * @param personDTO
     * @return
     */
    String updatePerson(String uuid, PersonDTO personDTO) throws ParseException;
}
