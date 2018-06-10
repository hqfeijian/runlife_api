package com.daohu.runlife.api.controller;

import com.daohu.runlife.api.constant.BizConstant;
import com.daohu.runlife.api.constant.SysConfig;
import com.daohu.runlife.api.domain.dto.banner.BannerOutput;
import com.daohu.runlife.api.domain.dto.banner.GetBannerByCondInput;
import com.daohu.runlife.api.domain.entity.Wallet;
import com.daohu.runlife.api.domain.model.JsonResult;
import com.daohu.runlife.api.domain.model.banner.BannerModel;
import com.daohu.runlife.api.repository.WalletMapper;
import com.daohu.runlife.api.service.BannerService;
import com.daohu.runlife.api.service.WalletService;
import com.daohu.runlife.api.utis.EncryptionUtil;
import com.daohu.runlife.api.utis.ObjectMapperUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import sun.security.provider.MD5;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/query")
    public JsonResult<List<BannerModel>> queryBanner(){
        GetBannerByCondInput input = new GetBannerByCondInput();
        input.setDeadline(new Date());
        input.setStatus(BizConstant.BannerStatus.ON_SHELVE);
        List<BannerOutput> result = bannerService.getBannerByCond(input);
        return new JsonResult<List<BannerModel>>(ObjectMapperUtil.mapList(result, BannerModel.class));
    }
}
