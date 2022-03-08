package me.lozm.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.lozm.common.dto.BaseDto;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoResponseDto extends BaseDto {

    private Long id;
    private String email;
    private String name;
    private List<OrderInfoResponseDto> orders;

}
