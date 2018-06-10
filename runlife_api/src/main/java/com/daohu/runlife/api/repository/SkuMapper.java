package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.Sku;

public interface SkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sku record);

    int insertSelective(Sku record);

    Sku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKeyWithBLOBs(Sku record);

    int updateByPrimaryKey(Sku record);
}