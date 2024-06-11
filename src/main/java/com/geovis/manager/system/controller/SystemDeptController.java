package com.geovis.manager.system.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.manager.system.dto.SystemDeptSaveDTO;
import com.geovis.manager.system.dto.SystemDeptUpdateDTO;
import com.geovis.manager.system.entity.SystemDept;
import com.geovis.manager.system.service.ISystemDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
 * 机构表 前端控制器
 * </p>
 *
 * @author hhj
 * @since 2021-01-18
 */
@RestController
@RequestMapping("/system/systemDept")
@RequiredArgsConstructor
@Api(value = "系统管理模块-机构信息相关接口", tags = "系统管理模块-机构信息相关接口")
@Slf4j
@Validated
public class SystemDeptController extends BaseController<ISystemDeptService> {

    @ApiOperation(value = "保存机构信息", notes = "保存机构信息")
    @PostMapping({"/save"})
    public Result save(@Validated @RequestBody SystemDeptSaveDTO saveDTO) {
        SystemDept systemDept = BeanUtil.toBean(saveDTO, SystemDept.class);
        baseService.save(systemDept);
        return Result.ok();
    }

    @ApiOperation(value = "修改机构信息", notes = "修改资源信息")
    @PostMapping({"/update"})
    public Result update(@Validated @RequestBody SystemDeptUpdateDTO updateDTO) {
        SystemDept systemDept = BeanUtil.toBean(updateDTO, SystemDept.class);
        baseService.updateById(systemDept);
        return Result.ok();
    }

    @ApiOperation(value = "查询所有机构数据", notes = "查询所有机构数据")
    @PostMapping("/list")
    public Result<List<SystemDept>> list() {
        return Result.ok(baseService.list(Wrappers.lambdaQuery(SystemDept.class).orderByAsc(SystemDept::getIdx)));
    }

    @ApiOperation(value = "批量删除机构数据", notes = "批量删除机构数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "idList", value = "删除id的list", required = true, dataType = "java.util.Set", paramType = "body")})
    @PostMapping("/removeByIdList")
    public Result removeByIdList(@NotNull(message = "删除的id集合不能为空") @RequestBody(required = false) Set<String> idList) {
        baseService.removeByIdList(idList);
        return Result.ok();
    }

}
