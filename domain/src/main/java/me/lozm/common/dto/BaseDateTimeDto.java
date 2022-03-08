package me.lozm.common.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseDateTimeDto {

    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

}
