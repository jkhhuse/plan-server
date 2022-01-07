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

    /**
     * 增加当天用户的头像信息
     * @param uuid
     * @param pictureLink
     * @return
     */
    String addPictureLink(String uuid, String pictureLink);

    /**
     * 获得当前用户的头像信息
     * @param uuid
     * @return
     */
    String getPictureLink(String uuid);
}
