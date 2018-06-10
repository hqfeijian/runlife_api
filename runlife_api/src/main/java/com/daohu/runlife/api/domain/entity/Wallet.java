package com.daohu.runlife.api.domain.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class Wallet {
    private Integer id;

    private String wxid;

    private String address;

    private String balance = "0";

    private String sessionKey;

    private Date createTime;

    private String walletJson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid == null ? null : wxid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWalletJson() {
        return walletJson;
    }

    public void setWalletJson(String walletJson) {
        this.walletJson = walletJson == null ? null : walletJson.trim();
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}