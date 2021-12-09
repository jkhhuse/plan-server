package com.jkhhuse.plan.service.person.impl;

import com.jkhhuse.plan.dao.person.PersonDao;
import com.jkhhuse.plan.dto.density.DensityDTO;
import com.jkhhuse.plan.dto.person.PersonDTO;
import com.jkhhuse.plan.entity.person.PersonDO;
import com.jkhhuse.plan.service.density.DensityService;
import com.jkhhuse.plan.service.person.PersonService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersonDao personDao;

    @Resource
    private DensityService densityService;

    @Resource
    private PasswordEncoder bcryptEncoder;

    @Override
    public PersonDO findPersonById(String uuid) {
        return personDao.findByUuid(uuid);
    }

    @Override
    public PersonDO findPersonByName(String name) {
        return personDao.findByName(name);
    }

    @Override
    public String addPerson(PersonDTO personDTO) throws ParseException {
        // 判断用户邮箱不能重复
        List<PersonDO> personList = personDao.findByEmail(personDTO.getEmail());
        if (!personList.isEmpty()) {
            return "该邮箱已被使用！";
        }

        // 判断用户邮箱不能重复
        PersonDO personDo = personDao.findByName(personDTO.getName());
        if (personDo != null) {
            return "该用户名已被使用，请更换其他用户名！";
        }

        // 插入人员数据
        PersonDO personDO = new PersonDO();
        personDO.setName(personDTO.getName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        personDO.setBornTime(format.parse(personDTO.getBornTime()));
        personDO.setOrigin(personDTO.getOrigin());
        personDO.setCreateTime(new Date());
        personDO.setAddr(personDTO.getAddr());
        personDO.setEmail(personDTO.getEmail());
        personDO.setPaaswd(bcryptEncoder.encode(personDTO.getPaaswd()));
        PersonDO result = personDao.save(personDO);

        // 插入数据信息
        DensityDTO densityDTO = new DensityDTO();
        densityDTO.setMeasureTime(personDTO.getBornTime());
        densityDTO.setMeasureValue(personDTO.getOrigin());
        densityService.addDensity(result.getUuid(), densityDTO);

        return result.getUuid();
    }

    @Override
    public String updatePerson(String uuid, PersonDTO personDTO) throws ParseException {
        // 验证密码是否正确
        List<PersonDO> list = personDao.findByPaaswdAndUuid(personDTO.getPaaswd(), String.valueOf(uuid));
        if (list.size() == 0) {
            return "用户密码输入错误";
        }

        PersonDO personDO = personDao.findByUuid(uuid);
        personDO.setName(personDTO.getName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        personDO.setBornTime(format.parse(personDTO.getBornTime()));
        personDO.setOrigin(personDTO.getOrigin());
        personDO.setCreateTime(new Date());
        personDO.setAddr(personDTO.getAddr());
        personDO.setEmail(personDTO.getEmail());
        personDO.setPaaswd(bcryptEncoder.encode(personDTO.getPaaswd()));
        personDao.save(personDO);
        return "更新成功";
    }
}
