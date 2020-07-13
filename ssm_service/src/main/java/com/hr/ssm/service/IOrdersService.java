package com.hr.ssm.service;

import com.hr.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page, int size) throws Exception;
}
