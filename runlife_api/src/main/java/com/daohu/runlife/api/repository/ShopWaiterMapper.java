package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.ShopWaiter;

public interface ShopWaiterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopWaiter record);

    int insertSelective(ShopWaiter record);

    ShopWaiter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopWaiter record);

    int updateByPrimaryKey(ShopWaiter record);
}