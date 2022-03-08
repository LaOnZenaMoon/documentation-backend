package me.lozm.user.dto;

import lombok.Getter;
import me.lozm.common.dto.BaseDto;

@Getter
public class UserCreateResponseDto extends BaseDto {

    private Long id;
    private String email;
    private String name;

}
