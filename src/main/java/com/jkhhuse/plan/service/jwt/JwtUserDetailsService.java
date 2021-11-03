package com.jkhhuse.plan.service.jwt;

import com.jkhhuse.plan.dao.person.PersonDao;
import com.jkhhuse.plan.entity.person.PersonDO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    private PersonDao personDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonDO personDO = personDao.findByName(username);
        if (personDO != null) {
            return new User(personDO.getName(), personDO.getPaaswd(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}