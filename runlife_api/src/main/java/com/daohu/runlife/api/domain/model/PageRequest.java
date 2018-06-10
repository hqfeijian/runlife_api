package com.daohu.runlife.api.domain.model;

import lombok.Data;

@Data
public class PageRequest {
    private Integer startIndex;
    private Integer pageSize;

    public Integer getPageIndex(){
        return startIndex/pageSize;
    }
}
