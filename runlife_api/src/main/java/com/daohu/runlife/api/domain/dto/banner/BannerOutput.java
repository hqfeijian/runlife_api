package com.daohu.runlife.api.domain.dto.banner;

import lombok.Data;

import java.util.Date;

@Data
public class BannerOutput {
    private Integer id;

    private String title;

    private Integer status;

    private Date createTime;
}
