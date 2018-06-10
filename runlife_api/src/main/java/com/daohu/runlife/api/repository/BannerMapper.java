package com.daohu.runlife.api.repository;

import com.daohu.runlife.api.domain.entity.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BannerMapper {
    List<Banner> getByCond(HashMap cond);
}