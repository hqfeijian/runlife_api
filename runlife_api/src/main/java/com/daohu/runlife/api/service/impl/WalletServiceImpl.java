package com.daohu.runlife.api.service.impl;

import com.daohu.runlife.api.domain.entity.Wallet;
import com.daohu.runlife.api.repository.WalletMapper;
import com.daohu.runlife.api.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletMapper walletMapper;
    @Override
    public boolean storeWallet(Wallet wallet) {
        walletMapper.insertSelective(wallet);
        return true;
    }

    @Override
    public Wallet getWalletByWxid(String wxid) {
        Wallet wallet = walletMapper.selectByWxid(wxid);
        return wallet;
    }
}
