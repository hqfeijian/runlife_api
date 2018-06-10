package com.daohu.runlife.api.domain.entity;

import java.util.Date;

public class OrderSelfPick {
    private Integer id;

    private Integer waiterId;

    private Integer orderId;

    private String takecode;

    private Date pickTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTakecode() {
        return takecode;
    }

    public void setTakecode(String takecode) {
        this.takecode = takecode == null ? null : takecode.trim();
    }

    public Date getPickTime() {
        return pickTime;
    }

    public void setPickTime(Date pickTime) {
        this.pickTime = pickTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}