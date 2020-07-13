package com.hr.ssm.service.impl;

import com.hr.ssm.dao.IOrdersDao;
import com.hr.ssm.domain.Orders;
import com.hr.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll() throws Exception {
        return ordersDao.findAll();
    }
}
