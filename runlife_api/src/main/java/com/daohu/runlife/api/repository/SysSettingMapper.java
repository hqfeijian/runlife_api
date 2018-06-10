package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.SysSetting;

public interface SysSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysSetting record);

    int insertSelective(SysSetting record);

    SysSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSetting record);

    int updateByPrimaryKey(SysSetting record);
}