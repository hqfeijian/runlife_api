package com.daohu.runlife.api.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> items;
    private Integer count;
}
