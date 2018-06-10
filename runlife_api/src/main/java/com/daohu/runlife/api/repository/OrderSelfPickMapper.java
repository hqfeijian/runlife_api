package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.OrderSelfPick;

public interface OrderSelfPickMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderSelfPick record);

    int insertSelective(OrderSelfPick record);

    OrderSelfPick selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderSelfPick record);

    int updateByPrimaryKey(OrderSelfPick record);
}