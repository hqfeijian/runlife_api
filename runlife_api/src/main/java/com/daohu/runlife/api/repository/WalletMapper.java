package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.Wallet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WalletMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Integer id);

    Wallet selectByWxid(String wxid);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKeyWithBLOBs(Wallet record);

    int updateByPrimaryKey(Wallet record);
}