package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.OrderExpress;

public interface OrderExpressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderExpress record);

    int insertSelective(OrderExpress record);

    OrderExpress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderExpress record);

    int updateByPrimaryKey(OrderExpress record);
}