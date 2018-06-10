package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.ExchangeStep;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExchangeStepMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExchangeStep record);

    int insertSelective(ExchangeStep record);

    ExchangeStep selectByPrimaryKey(Integer id);

    ExchangeStep selectCurrentDateByAddress(String address);

    int updateByPrimaryKeySelective(ExchangeStep record);

    int updateByPrimaryKey(ExchangeStep record);
}