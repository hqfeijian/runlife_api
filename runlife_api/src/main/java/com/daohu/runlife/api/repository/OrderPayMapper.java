package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.OrderPay;

public interface OrderPayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    OrderPay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);
}