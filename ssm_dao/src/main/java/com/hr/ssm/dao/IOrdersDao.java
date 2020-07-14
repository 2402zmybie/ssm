package com.hr.ssm.dao;

import com.hr.ssm.domain.Member;
import com.hr.ssm.domain.Orders;
import com.hr.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            //id = true,表示 数据库中id这个属性是主键
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.hr.ssm.dao.IProductDao.findById"))

    })
    List<Orders> findAll() throws Exception;


    //多表操作
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            //一对一 (一个订单一个产品)
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.hr.ssm.dao.IProductDao.findById")),
            //一对一 (一个订单一个会员)
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.hr.ssm.dao.IMemberDao.findById")),
            //多对多 (一个订单有多个游客, 要使用中间表)
            @Result(property = "travellers",column = "id",javaType = List.class,many = @Many(select = "com.hr.ssm.dao.ITravellereDao.findByOrdersId"))

    })
    Orders findById(String ordersId);
}
