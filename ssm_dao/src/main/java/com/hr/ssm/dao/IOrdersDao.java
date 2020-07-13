package com.hr.ssm.dao;

import com.hr.ssm.domain.Orders;
import com.hr.ssm.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
}
