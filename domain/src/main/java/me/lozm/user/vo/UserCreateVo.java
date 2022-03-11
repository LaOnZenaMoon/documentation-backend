package me.lozm.user.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.lozm.common.vo.BaseVo;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class UserCreateVo extends BaseVo {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String encryptedPassword;

}
