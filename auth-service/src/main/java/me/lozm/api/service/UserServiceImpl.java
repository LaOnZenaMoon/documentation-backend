package me.lozm.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lozm.common.vo.PageVo;
import me.lozm.common.vo.SearchVo;
import me.lozm.user.entity.User;
import me.lozm.user.repository.UserRepository;
import me.lozm.user.service.UserHelperService;
import me.lozm.user.vo.OrderInfoVo;
import me.lozm.user.vo.UserCreateVo;
import me.lozm.user.vo.UserInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static me.lozm.utils.MapperUtils.mapStrictly;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserHelperService userHelperService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public Page<UserInfoVo> getUserList(PageVo pageVo, SearchVo searchVo) {
        List<UserInfoVo> userList = userRepository.getUserList(pageVo, searchVo);
        long totalCount = userRepository.getUserTotalCount(pageVo, searchVo);
        return new PageImpl<>(userList, pageVo.getPageRequest(), totalCount);
    }

    @Override
    public UserInfoVo getUserDetail(Long userId) {
        User user = userHelperService.getUser(userId);
        UserInfoVo userInfoVo = mapStrictly(user, UserInfoVo.class);

        List<OrderInfoVo> orderList = new ArrayList<>();
        userInfoVo.setOrders(orderList);
        return userInfoVo;
    }

    @Override
    @Transactional
    public UserCreateVo createUser(UserCreateVo userCreateVo) {
        User user = mapStrictly(userCreateVo, User.class);
        user.encryptPassword(passwordEncoder.encode(userCreateVo.getPassword()));
        User savedUser = userRepository.save(user);
        return mapStrictly(savedUser, UserCreateVo.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userHelperService.getUser(username);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getEncryptedPassword(),
                true, true, true, true, new ArrayList<>()
        );
    }

    @Override
    public UserInfoVo findByEmail(String email) {
        User user = userHelperService.getUser(email);
        return mapStrictly(user, UserInfoVo.class);
    }

}
