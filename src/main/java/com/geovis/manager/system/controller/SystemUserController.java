package com.geovis.manager.system.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.auth.props.AuthProperties;
import com.geovis.common.auth.util.AuthUtil;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.constant.CacheConstants;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.system.dto.*;
import com.geovis.manager.system.entity.SystemUser;
import com.geovis.manager.system.service.ISystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.time.temporal.ChronoUnit;
import java.util.Set;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 王响
 * @since 2020-07-15
 */
@RestController
@RequestMapping("/system/systemUser")
@RequiredArgsConstructor
@Api(value = "系统管理模块-用户信息相关接口", tags = "系统管理模块-用户信息相关接口")
@Slf4j
@Validated
public class SystemUserController extends BaseController<ISystemUserService> {

    private final PasswordEncoder passwordEncoder;

    private final RedisTemplate redisTemplate;

    @ApiOperation(value = "设置用户超时时间", notes = "设置用户超时时间")
    @GetMapping("/setUseTokenExpireTime/{seconds}")
    public Result setUseTokenExpireTime(@PathVariable("seconds") Long seconds) {
        Assert.isFalse(seconds <= 0, "过期时间不能小于等于0");
        String currentUserId = AuthUtil.getCurrentUserId();
        redisTemplate.opsForValue().set(CacheConstants.CACHE_USER_PREFIX_KEY + ":expire_time:" + currentUserId, seconds * 1000);
        // 登出
        try {
            redisTemplate.delete(CacheConstants.CACHE_USER_PREFIX_KEY + currentUserId);
        } catch (Exception e) {

        }
        return Result.ok();
    }

    @ApiOperation(value = "获取当前登录用户信息", notes = "获取当前登录用户信息")
    @GetMapping("/getCurrUserInfo")
    public Result<LoginUserInfoDTO> getCurrUserInfo() {
        String currentUsername = AuthUtil.getCurrentUsername();
        if (ObjectUtil.isEmpty(currentUsername)) {
            return Result.failed("未查到用户信息");
        }
        SystemUser systemUser = baseService.getOne(Wrappers.lambdaQuery(SystemUser.class).eq(SystemUser::getUserName, currentUsername));
        LoginUserInfoDTO loginUserInfoDTO = baseService.getByUserId(systemUser.getId());
        if (ObjectUtil.isNotEmpty(systemUser) && ObjectUtil.isNotEmpty(systemUser.getPasswordLastChangeTime())) {
            // 校验时间
            long between = LocalDateTimeUtil.between(systemUser.getPasswordLastChangeTime(), LocalDateTimeUtil.now(), ChronoUnit.DAYS);
            if (between > 90) {
                // 密码已使用超过3个月，请修改密码后再进行操作
                return Result.ok(loginUserInfoDTO, "508");
            }
        }
        return Result.ok(loginUserInfoDTO);
    }

    @ApiOperation(value = "保存用户信息", notes = "保存用户信息")
    @PostMapping({"/save"})
    public Result<String> save(@Validated @RequestBody SystemUserSaveDTO saveDTO) {
        // 1、检查用户名是否已存在
        Long saveUsernameCount = baseService.count(Wrappers.lambdaQuery(SystemUser.class).eq(SystemUser::getUserName, saveDTO.getUserName()));
        if (saveUsernameCount > 0) {
            return Result.failed(StrUtil.format("用户名【{}】已被使用！", saveDTO.getUserName()));
        }

        // 2、检查手机号是否已存在
        Long savePhoneCount = baseService.count(Wrappers.lambdaQuery(SystemUser.class).eq(SystemUser::getPhone, saveDTO.getPhone()));
        if (savePhoneCount > 0) {
            return Result.failed(StrUtil.format("手机号码【{}】已被使用！", saveDTO.getPhone()));
        }

        // 3、检查邮箱是否已存在
        Long saveEmailCount = baseService.count(Wrappers.lambdaQuery(SystemUser.class).eq(SystemUser::getEmail, saveDTO.getEmail()));
        if (saveEmailCount > 0) {
            return Result.failed(StrUtil.format("邮箱【{}】已被使用！", saveDTO.getEmail()));
        }

        if (StrUtil.isNotEmpty(saveDTO.getUserPassword())) {
            saveDTO.setUserPassword(passwordEncoder.encode(saveDTO.getUserPassword()));
        }
        SystemUser systemUser = BeanUtil.toBean(saveDTO, SystemUser.class);
        systemUser.setDataStatus(CommonConstants.YES);
        baseService.save(systemUser);
        return Result.ok();
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PostMapping({"/update"})
    public Result update(@Validated @RequestBody SystemUserUpdateDTO updateDTO) {
        // 1、检查用户名是否已存在
        Long updateUsernameCount = baseService.count(Wrappers.lambdaQuery(SystemUser.class)
                .eq(SystemUser::getUserName, updateDTO.getUserName())
                .ne(SystemUser::getId, updateDTO.getId()));
        if (updateUsernameCount > 0) {
            return Result.failed(StrUtil.format("用户名【{}】已被使用！", updateDTO.getUserName()));
        }

        // 2、检查手机号是否已存在
        Long updatePhoneCount = baseService.count(Wrappers.lambdaQuery(SystemUser.class)
                .eq(SystemUser::getPhone, updateDTO.getPhone())
                .ne(SystemUser::getId, updateDTO.getId()));
        if (updatePhoneCount > 0) {
            return Result.failed(StrUtil.format("手机号码【{}】已被使用！", updateDTO.getPhone()));
        }

        SystemUser systemUser = BeanUtil.toBean(updateDTO, SystemUser.class);
        baseService.updateById(systemUser);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询用户信息", notes = "分页查询用户信息")
    @PostMapping("/listPage")
    public Result<PageResult<SystemUserDTO>> listPage(@RequestBody @Validated PageParam<SystemUserQueryDTO> pageParam) {
        return Result.ok(baseService.listPage(pageParam));
    }

    @ApiOperation(value = "批量删除数据", notes = "批量删除数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "idList", value = "删除id的list", required = true, dataType = "java.util.Set", paramType = "body")})
    @PostMapping("/removeByIdList")
    public Result removeByIdList(@NotNull(message = "删除的id集合不能为空") @RequestBody(required = false) Set<String> idList) {
        baseService.removeByIdList(idList);
        return Result.ok();
    }

    @ApiOperation(value = "修改当前用户密码", notes = "修改当亲用户密码")
    @PostMapping("/changePassword")
    public Result<String> changePassword(@Validated @RequestBody SystemUserChangePasswordDTO changePasswordDTO) {

        SystemUser systemUser = baseService.getById(AuthUtil.getCurrentUserId());
        if (ObjectUtil.isEmpty(systemUser)) {
            return Result.failed("没有查询到此用户");
        }
        // 检查旧密码是否一致
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), systemUser.getUserPassword())) {
            return Result.failed("旧密码不正确");
        }
        // 设置新密码
        systemUser.setUserPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()))
                .setPasswordLastChangeTime(LocalDateTimeUtil.now());

        baseService.updateById(systemUser);
        return Result.ok();
    }

    @ApiOperation(value = "分配角色", notes = "分配角色")
    @PostMapping("/assignRole")
    public Result assignRole(@RequestBody SystemUserAssignRoleDTO assignRoleDTO) {
        baseService.assignRole(assignRoleDTO);
        return Result.ok();
    }

}
