package com.daohu.runlife.api.service;

import com.daohu.runlife.api.domain.entity.Wallet;

public interface WalletService {
    //存储账号wxid + 钱包json
    boolean storeWallet(Wallet wallet);
    Wallet getWalletByWxid(String wxid);
}
