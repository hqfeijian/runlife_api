package com.daohu.runlife.api.service.impl;

import com.daohu.runlife.api.domain.entity.ExchangeStep;
import com.daohu.runlife.api.repository.ExchangeStepMapper;
import com.daohu.runlife.api.repository.WalletMapper;
import com.daohu.runlife.api.service.ExchangeStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExchangeStepServiceImpl implements ExchangeStepService{

    @Autowired
    private ExchangeStepMapper exchangeStepMapper;
    @Override
    public void storeExchangeRecord(String address, int step, double stepCoin) {
        ExchangeStep exchangeStep = new ExchangeStep();
        exchangeStep.setAddress(address);
        exchangeStep.setExchangeStep(step);
        exchangeStep.setStepCoin(stepCoin);
        exchangeStep.setCreateTime(new Date());
        exchangeStepMapper.insertSelective(exchangeStep);
    }

    @Override
    public ExchangeStep getCurrentDateRecordByAddress(String address) {
        return exchangeStepMapper.selectCurrentDateByAddress(address);
    }
}
