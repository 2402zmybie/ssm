package com.hr.ssm.dao;

import com.hr.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    @Select("select * from user")
    List<UserInfo> findAll() throws Exception;
}
