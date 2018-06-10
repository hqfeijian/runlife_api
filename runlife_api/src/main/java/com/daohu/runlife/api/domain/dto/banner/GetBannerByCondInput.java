package com.daohu.runlife.api.domain.dto.banner;

import lombok.Data;

import java.util.Date;

@Data
public class GetBannerByCondInput {
    private Integer status;
    private Date deadline;
}
