package com.daohu.runlife.api.domain.entity;

public class Product extends ProductKey {
    private String name;

    private Integer status;

    private Boolean valid;

    private Integer cateogryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Integer getCateogryId() {
        return cateogryId;
    }

    public void setCateogryId(Integer cateogryId) {
        this.cateogryId = cateogryId;
    }
}