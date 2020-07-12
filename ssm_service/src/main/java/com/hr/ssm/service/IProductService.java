package com.hr.ssm.service;

import com.hr.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll() throws Exception;
}
