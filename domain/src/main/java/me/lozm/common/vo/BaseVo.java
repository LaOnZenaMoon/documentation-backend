package me.lozm.common.vo;

import lombok.Getter;

@Getter
public abstract class BaseVo extends BaseDateTimeVo {

    private Long createdBy;
    private Long lastModifiedBy;
    private Boolean use;

}
