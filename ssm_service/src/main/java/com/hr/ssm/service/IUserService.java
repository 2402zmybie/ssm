package com.hr.ssm.service;

import com.hr.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;
}
