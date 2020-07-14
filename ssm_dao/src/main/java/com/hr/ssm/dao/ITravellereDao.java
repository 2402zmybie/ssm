package com.hr.ssm.dao;

import com.hr.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellereDao {

    @Select("select * from traveller where id in (select travellerid from order_traveller where orderId=#{ordersId})")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
