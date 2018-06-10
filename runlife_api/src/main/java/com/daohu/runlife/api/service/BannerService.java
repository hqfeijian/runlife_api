package com.daohu.runlife.api.service;

import com.daohu.runlife.api.domain.dto.banner.BannerOutput;
import com.daohu.runlife.api.domain.dto.banner.GetBannerByCondInput;

import java.util.List;

public interface BannerService {
    List<BannerOutput> getBannerByCond(GetBannerByCondInput input);
}
