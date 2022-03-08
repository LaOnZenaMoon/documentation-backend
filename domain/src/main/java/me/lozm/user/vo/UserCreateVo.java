package me.lozm.user.vo;

import lombok.Getter;
import me.lozm.common.vo.BaseVo;

@Getter
public class UserCreateVo extends BaseVo {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String encryptedPassword;

}
