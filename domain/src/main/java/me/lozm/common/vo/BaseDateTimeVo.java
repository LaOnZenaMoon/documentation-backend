package me.lozm.common.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseDateTimeVo {

    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

}
