package com.geovis.manager.system.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.system.dto.SystemModuleDTO;
import com.geovis.manager.system.dto.SystemUserDTO;
import com.geovis.manager.system.entity.SystemModule;
import com.geovis.manager.system.entity.SystemModuleResources;
import com.geovis.manager.system.service.ISystemModuleResourcesService;
import com.geovis.manager.system.service.ISystemModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统模块 前端控制器
 * </p>
 *
 * @author 王响
 * @since 2021-01-18
 */
@RestController
@RequestMapping("/system/systemModule")
@RequiredArgsConstructor
@Api(value = "系统管理模块-模块管理", tags = "系统管理模块-模块管理")
@Slf4j
@Validated
public class SystemModuleController extends BaseController<ISystemModuleService> {

    private final ISystemModuleResourcesService systemModuleResourcesService;

    @ApiOperation(value = "获取全部模块", notes = "获取全部模块")
    @GetMapping("/getAll")
    public Result<?> getAll() {
        return Result.ok(baseService.selectAll());
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/listPage")
    public Result<PageResult<SystemUserDTO>> listPage(@RequestBody @Validated PageParam<SystemModule> pageParam) {
        return Result.ok(new PageResult(baseService.page(new Page<>(pageParam.getCurrent(), pageParam.getSize()))));
    }

    @ApiOperation(value = "保存资源信息", notes = "保存资源信息")
    @PostMapping({"/save"})
    public Result save(@Validated @RequestBody SystemModule saveDTO) {
        baseService.save(saveDTO);
        return Result.ok();
    }

    @ApiOperation(value = "修改资源信息", notes = "修改资源信息")
    @PostMapping({"/update"})
    public Result update(@Validated @RequestBody SystemModule updateDTO) {
        baseService.updateById(updateDTO);
        return Result.ok();
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/removeByIdList")
    public Result<?> removeByIdList(@NotNull(message = "删除的id集合不能为空") @RequestBody(required = false) Set<String> idList) {
        baseService.removeByIds(idList);
        systemModuleResourcesService.remove(Wrappers.lambdaQuery(SystemModuleResources.class).in(SystemModuleResources::getModuleId,idList));
        return Result.ok();
    }

    @ApiOperation(value = "设置目录", notes = "设置目录")
    @PostMapping("/settingResources")
    public Result<?> settingResources(@RequestBody SystemModuleDTO dto) {
        baseService.settingResources(dto);
        return Result.ok();
    }

    @ApiOperation(value = "设置目录", notes = "设置目录")
    @GetMapping("/listResourcesByModuleId/{id}")
    public Result<?> listResourcesByModuleId(@PathVariable("id") String id) {
        List<SystemModuleResources> list = systemModuleResourcesService.list(Wrappers.lambdaQuery(SystemModuleResources.class).eq(SystemModuleResources::getModuleId, id));
        return Result.ok(list);
    }

    @ApiOperation(value = "根据模块id获取资源", notes = "根据模块id获取资源")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "模块id", required = true, dataType = "java.lang.Long", paramType = "path")})
    @GetMapping("/getResources")
    public Result<?> getResources(@NotNull(message = "模块id不能为空") String id) {
        return Result.ok(baseService.getResources(id));
    }
}
