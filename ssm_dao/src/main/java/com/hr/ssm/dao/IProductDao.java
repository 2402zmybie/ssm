package com.hr.ssm.dao;

import com.hr.ssm.domain.Product;

import java.util.List;

public interface IProductDao {
    /**
     * 查询所有产品信息
     * @return
     * @throws Exception
     */
    public List<Product> findAll() throws Exception;


}
