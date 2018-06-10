package com.daohu.runlife.api.service;

import com.daohu.runlife.api.domain.entity.ExchangeStep;

public interface ExchangeStepService {
    void storeExchangeRecord(String address,int step,double stepCoin);
    ExchangeStep getCurrentDateRecordByAddress(String address);
}
