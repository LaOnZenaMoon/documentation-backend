package me.lozm.user.repository;

import me.lozm.common.vo.PageVo;
import me.lozm.common.vo.SearchVo;
import me.lozm.user.vo.UserInfoVo;

import java.util.List;

public interface UserRepositoryCustom {

    List<UserInfoVo> getUserList(PageVo pageVo, SearchVo searchVo);

    long getUserTotalCount(PageVo pageVo, SearchVo searchVo);

}
