package me.lozm.common.dto;

import lombok.Getter;

@Getter
public abstract class BaseDto extends BaseDateTimeDto {

    private Long createdBy;
    private Long lastModifiedBy;
    private Boolean use;

}
