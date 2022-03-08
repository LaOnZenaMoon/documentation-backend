package me.lozm.api.service;

import me.lozm.common.vo.PageVo;
import me.lozm.common.vo.SearchVo;
import me.lozm.user.vo.UserCreateVo;
import me.lozm.user.vo.UserInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserCreateVo createUser(UserCreateVo userCreateVo);

    Page<UserInfoVo> getUserList(PageVo pageVo, SearchVo searchVo);

    UserInfoVo getUserDetail(Long userId);

    UserInfoVo findByEmail(String email);

}
