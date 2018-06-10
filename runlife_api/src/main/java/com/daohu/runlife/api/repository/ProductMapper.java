package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.Product;
import com.daohu.runlife.api.domain.entity.ProductKey;

public interface ProductMapper {
    int deleteByPrimaryKey(ProductKey key);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(ProductKey key);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}