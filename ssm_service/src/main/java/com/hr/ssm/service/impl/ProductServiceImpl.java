package com.hr.ssm.service.impl;

import com.hr.ssm.dao.IProductDao;
import com.hr.ssm.domain.Product;
import com.hr.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao prouctDao;

    @Override
    public List<Product> findAll() throws Exception {
        return prouctDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        prouctDao.save(product);
    }
}
