package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.SkuImage;

public interface SkuImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkuImage record);

    int insertSelective(SkuImage record);

    SkuImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkuImage record);

    int updateByPrimaryKeyWithBLOBs(SkuImage record);

    int updateByPrimaryKey(SkuImage record);
}