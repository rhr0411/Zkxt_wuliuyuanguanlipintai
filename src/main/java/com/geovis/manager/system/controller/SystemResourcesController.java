package com.geovis.manager.system.controller;


import cn.hutool.core.bean.BeanUtil;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.manager.system.dto.SystemResourcesDTO;
import com.geovis.manager.system.dto.SystemResourcesQueryDTO;
import com.geovis.manager.system.dto.SystemResourcesSaveDTO;
import com.geovis.manager.system.dto.SystemResourcesUpdateDTO;
import com.geovis.manager.system.entity.SystemResources;
import com.geovis.manager.system.service.ISystemResourcesService;
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
 * 系统资源表 前端控制器
 * </p>
 *
 * @author 王响
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/system/systemResources")
@AllArgsConstructor
@Api(value = "系统管理模块-系统资源相关接口", tags = "系统管理模块-系统资源相关接口")
@Slf4j
@Validated
public class SystemResourcesController extends BaseController<ISystemResourcesService> {

    @ApiOperation(value = "保存资源信息", notes = "保存资源信息")
    @PostMapping({"/save"})
    public Result save(@Validated @RequestBody SystemResourcesSaveDTO saveDTO) {
        SystemResources systemResources = BeanUtil.toBean(saveDTO, SystemResources.class);
        baseService.save(systemResources);
        return Result.ok();
    }

    @ApiOperation(value = "修改资源信息", notes = "修改资源信息")
    @PostMapping({"/update"})
    public Result update(@Validated @RequestBody SystemResourcesUpdateDTO updateDTO) {
        SystemResources systemResources = BeanUtil.toBean(updateDTO, SystemResources.class);
        baseService.updateById(systemResources);
        return Result.ok();
    }

    @ApiOperation(value = "查询所有资源数据", notes = "查询所有资源数据")
    @PostMapping("/list")
    public Result<List<SystemResourcesDTO>> list(@RequestBody @Validated SystemResourcesQueryDTO queryDTO) {
        return Result.ok(baseService.list(queryDTO));
    }

    @ApiOperation(value = "批量删除资源数据", notes = "批量删除资源数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "idList", value = "删除id的list", required = true, dataType = "java.util.Set", paramType = "body")})
    @PostMapping("/removeByIdList")
    public Result removeByIdList(@NotNull(message = "删除的id集合不能为空") @RequestBody(required = false) Set<String> idList) {
        baseService.removeByIdList(idList);
        return Result.ok();
    }
}
