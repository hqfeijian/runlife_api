package com.daohu.runlife.api.domain.entity;

import java.util.Date;

public class ExchangeStep {
    private Integer id;

    private String address;

    private Integer exchangeStep;

    private Date createTime;

    private Double stepCoin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getExchangeStep() {
        return exchangeStep;
    }

    public void setExchangeStep(Integer exchangeStep) {
        this.exchangeStep = exchangeStep;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getStepCoin() {
        return stepCoin;
    }

    public void setStepCoin(Double stepCoin) {
        this.stepCoin = stepCoin;
    }
}