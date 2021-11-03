package com.jkhhuse.plan.controller.auth;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.dto.auth.AuthDTO;
import com.jkhhuse.plan.dto.person.PersonDTO;
import com.jkhhuse.plan.service.person.PersonService;
import com.jkhhuse.plan.utils.JwtTokenUtil;
import com.jkhhuse.plan.vo.person.PersonVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Api("desc of class")
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class JwtAuthenticationController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Resource
    private PersonService personService;

    @ApiOperation(value = "用户注册", notes = "新增用户信息")
    @PostMapping(value = "/register", consumes = "application/json")
    CommonResponse<List<PersonVO>> register(
            @ApiParam(value = "用户信息", required = true) @Valid @RequestBody PersonDTO personDTO) {
        String message = "";
        try {
            message = personService.addPerson(personDTO);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommonResponse("200", "", message);
    }

    @ApiOperation(value = "权限认证", notes = "获得 jwt token")
    @PostMapping(value = "/authenticate", consumes = "application/json")
    public CommonResponse<?> createAuthenticationToken(
            @ApiParam(value = "用户信息", required = true) @Valid @RequestBody AuthDTO authDTO)
            throws Exception {

        authenticate(authDTO.getUsername(), authDTO.getPasswd());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authDTO.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new CommonResponse("200", token, "");
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}