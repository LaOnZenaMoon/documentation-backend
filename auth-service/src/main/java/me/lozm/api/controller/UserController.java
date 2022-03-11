package me.lozm.api.controller;

import lombok.RequiredArgsConstructor;
import me.lozm.api.service.UserService;
import me.lozm.common.dto.PageDto;
import me.lozm.common.dto.SearchDto;
import me.lozm.common.vo.PageVo;
import me.lozm.common.vo.SearchVo;
import me.lozm.user.dto.UserCreateRequestDto;
import me.lozm.user.dto.UserCreateResponseDto;
import me.lozm.user.dto.UserInfoResponseDto;
import me.lozm.user.vo.UserCreateVo;
import me.lozm.user.vo.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static me.lozm.utils.MapperUtils.mapStrictly;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<Page<UserInfoResponseDto>> getUserList(PageDto pageDto, SearchDto searchDto) {
        final PageVo pageVo = mapStrictly(pageDto, PageVo.class);
        final SearchVo searchVo = mapStrictly(searchDto, SearchVo.class);

        Page<UserInfoVo> usersPage = userService.getUserList(pageVo, searchVo);

        List<UserInfoResponseDto> responseList = usersPage.getContent()
                .stream()
                .map(vo -> mapStrictly(vo, UserInfoResponseDto.class))
                .collect(toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl<>(responseList, usersPage.getPageable(), usersPage.getTotalElements()));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserInfoResponseDto> getUserDetail(@PathVariable("userId") Long userId) {
        UserInfoVo userInfoVo = userService.getUserDetail(userId);

        UserInfoResponseDto userInfoResponseDto = mapStrictly(userInfoVo, UserInfoResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoResponseDto);
    }

    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUser(@RequestHeader Map<String, Object> requestHeader,
            @RequestBody @Validated UserCreateRequestDto requestDto) {

        validateJwt(requestHeader);

        UserCreateVo userCreateVo = mapStrictly(requestDto, UserCreateVo.class);
        UserCreateVo responseUserCreateVo = userService.createUser(userCreateVo);

        UserCreateResponseDto userCreateResponseDto = mapStrictly(responseUserCreateVo, UserCreateResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userCreateResponseDto);
    }


    private void validateJwt(Map<String, Object> requestHeader) {
        String authorization = (String) requestHeader.get(HttpHeaders.AUTHORIZATION);
        authorization = StringUtils.isEmpty(authorization) ? (String) requestHeader.get(HttpHeaders.AUTHORIZATION.toLowerCase()) : authorization;

        if (StringUtils.isEmpty(authorization)) {
            throw new IllegalArgumentException();
        }
    }

}
