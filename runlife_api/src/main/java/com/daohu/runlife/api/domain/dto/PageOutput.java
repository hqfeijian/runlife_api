package com.daohu.runlife.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageOutput<T> {
    private List<T> items;
    private Integer count;
}
