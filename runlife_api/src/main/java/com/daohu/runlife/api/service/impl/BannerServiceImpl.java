package com.daohu.runlife.api.service.impl;

import com.daohu.runlife.api.domain.dto.banner.BannerOutput;
import com.daohu.runlife.api.domain.dto.banner.GetBannerByCondInput;
import com.daohu.runlife.api.domain.entity.Banner;
import com.daohu.runlife.api.repository.BannerMapper;
import com.daohu.runlife.api.service.BannerService;
import com.daohu.runlife.api.utis.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerOutput> getBannerByCond(GetBannerByCondInput input) {
        HashMap map = new HashMap();
        map.put("status",input.getStatus());
        map.put("deadline", input.getDeadline());

        List<Banner> banners = bannerMapper.getByCond(map);

        return ObjectMapperUtil.mapList(banners,BannerOutput.class);
    }
}
