package com.geovis.manager.system.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.manager.system.dto.SystemRoleAssignResourceDTO;
import com.geovis.manager.system.dto.SystemRoleSaveDTO;
import com.geovis.manager.system.dto.SystemRoleUpdateDTO;
import com.geovis.manager.system.entity.SystemRole;
import com.geovis.manager.system.service.ISystemRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author 王响
 * @since 2020-07-15
 */
@RestController
@RequestMapping("/system/systemRole")
@AllArgsConstructor
@Api(value = "系统管理模块-角色相关接口", tags = "系统管理模块-角色相关接口")
@Slf4j
@Validated
public class SystemRoleController extends BaseController<ISystemRoleService> {

    @ApiOperation(value = "保存角色信息", notes = "保存角色信息")
    @PostMapping({"/save"})
    public Result save(@Validated @RequestBody SystemRoleSaveDTO saveDTO) {

        // 1、检查角色名称是否已存在
        Long saveCheckNameCount = baseService.count(Wrappers.lambdaQuery(SystemRole.class).eq(SystemRole::getName, saveDTO.getName()));
        if (saveCheckNameCount > 0) {
            return Result.failed(StrUtil.format("角色名称【{}】已被使用！", saveDTO.getName()));
        }

        // 2、检查角色英文名称是否已存在
        Long saveCheckEnNameCount = baseService.count(Wrappers.lambdaQuery(SystemRole.class).eq(SystemRole::getEnName, saveDTO.getEnName()));
        if (saveCheckEnNameCount > 0) {
            return Result.failed(StrUtil.format("角色英文名称【{}】已被使用！", saveDTO.getEnName()));
        }
        SystemRole systemRole = BeanUtil.toBean(saveDTO, SystemRole.class);
        baseService.save(systemRole);
        return Result.ok();
    }

    @ApiOperation(value = "修改角色信息", notes = "修改角色信息")
    @PostMapping({"/update"})
    public Result update(@Validated @RequestBody SystemRoleUpdateDTO updateDTO) {
        // 1、检查角色名称是否已存在
        Long updateCheckNameCount = baseService.count(Wrappers.lambdaQuery(SystemRole.class)
                .eq(SystemRole::getName, updateDTO.getName())
                .ne(SystemRole::getId, updateDTO.getId()));
        if (updateCheckNameCount > 0) {
            return Result.failed(StrUtil.format("角色名称【{}】已被使用！", updateDTO.getName()));
        }

        // 2、检查角色英文名称是否已存在
        Long updateCheckEnNameCount = baseService.count(Wrappers.lambdaQuery(SystemRole.class)
                .eq(SystemRole::getEnName, updateDTO.getEnName())
                .ne(SystemRole::getId, updateDTO.getId()));
        if (updateCheckEnNameCount > 0) {
            return Result.failed(StrUtil.format("角色英文名称【{}】已被使用！", updateDTO.getEnName()));
        }
        SystemRole systemRole = BeanUtil.toBean(updateDTO, SystemRole.class);
        baseService.updateById(systemRole);
        return Result.ok();
    }

    @ApiOperation(value = "查询所有角色数据", notes = "查询所有角色数据")
    @PostMapping("/list")
    public Result<List<SystemRole>> list() {
        return Result.ok(baseService.list(Wrappers.lambdaQuery(SystemRole.class).orderByAsc(SystemRole::getIdx)));
    }

    @ApiOperation(value = "批量删除数据", notes = "批量删除数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "idList", value = "删除id的list", required = true, dataType = "java.util.Set", paramType = "body")})
    @PostMapping("/removeByIdList")
    public Result removeByIdList(@NotNull(message = "删除的id集合不能为空") @RequestBody(required = false) Set<String> idList) {
        baseService.removeByIdList(idList);
        return Result.ok();
    }

    @ApiOperation(value = "设置资源", notes = "设置资源")
    @PostMapping("/assignResource")
    public Result assignResource(@RequestBody SystemRoleAssignResourceDTO assignResourceDTO) {
        baseService.assignResource(assignResourceDTO);
        return Result.ok();
    }
}
