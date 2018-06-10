package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.OrderSku;

public interface OrderSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderSku record);

    int insertSelective(OrderSku record);

    OrderSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderSku record);

    int updateByPrimaryKeyWithBLOBs(OrderSku record);

    int updateByPrimaryKey(OrderSku record);
}