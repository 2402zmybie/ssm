package com.hr.ssm.dao;

import com.hr.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {

    /**
     * 根据id查产品信息
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    public Product findById(String id) throws Exception;

    /**
     * 查询所有产品信息
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
