package com.geovis.manager.system.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.system.dto.SystemDictQueryDTO;
import com.geovis.manager.system.dto.SystemDictSaveDTO;
import com.geovis.manager.system.dto.SystemDictUpdateDTO;
import com.geovis.manager.system.entity.SystemDict;
import com.geovis.manager.system.service.ISystemDictService;
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
 * 字典表 前端控制器
 * </p>
 *
 * @author 王响
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/system/systemDict")
@AllArgsConstructor
@Api(value = "系统管理模块-字典信息相关接口", tags = "系统管理模块-字典信息相关接口")
@Slf4j
@Validated
public class SystemDictController extends BaseController<ISystemDictService> {

    @ApiOperation(value = "保存字典信息", notes = "保存字典信息")
    @PostMapping({"/save"})
    public Result save(@Validated @RequestBody SystemDictSaveDTO saveDTO) {
        LambdaQueryWrapper<SystemDict> wrapper = Wrappers.lambdaQuery(SystemDict.class)
                .and(lambdaQueryWrapper -> lambdaQueryWrapper.eq(SystemDict::getCode, saveDTO.getCode())
                        .or()
                        .eq(SystemDict::getName, saveDTO.getName()));
        Long count = baseService.count(wrapper);
        if (count > 0) {
            return Result.failed(StrUtil.format("该编码【{}】或者名称【{}】已被使用！", saveDTO.getCode(), saveDTO.getName()));
        }

        SystemDict systemDict = BeanUtil.toBean(saveDTO, SystemDict.class);
        systemDict.setDataStatus(CommonConstants.YES);
        baseService.save(systemDict);
        return Result.ok();
    }

    @ApiOperation(value = "修改字典信息", notes = "修改字典信息")
    @PostMapping({"/update"})
    public Result update(@Validated @RequestBody SystemDictUpdateDTO updateDTO) {
        LambdaQueryWrapper<SystemDict> wrapper = Wrappers.lambdaQuery(SystemDict.class)
                .and(lambdaQueryWrapper -> lambdaQueryWrapper.eq(SystemDict::getCode, updateDTO.getCode())
                        .or()
                        .eq(SystemDict::getName, updateDTO.getName())).ne(SystemDict::getId, updateDTO.getId());
        Long count = baseService.count(wrapper);
        if (count > 0) {
            return Result.failed(StrUtil.format("该编码【{}】或者名称【{}】已被使用！", updateDTO.getCode(), updateDTO.getName()));
        }

        SystemDict systemDict = BeanUtil.toBean(updateDTO, SystemDict.class);
        baseService.updateById(systemDict);
        return Result.ok();
    }

    @ApiOperation(value = "查询字典分页数据", notes = "查询字典分页数据")
    @PostMapping("/listPage")
    public Result<PageResult<SystemDict>> listPage(@RequestBody @Validated PageParam<SystemDictQueryDTO> pageParam) {
        IPage<SystemDict> page = pageParam.buildPage();
        SystemDictQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<SystemDict> wrapper = Wrappers.lambdaQuery(SystemDict.class).orderByAsc(SystemDict::getType).orderByAsc(SystemDict::getCode);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getCode()), SystemDict::getCode, queryDTO.getCode());
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getName()), SystemDict::getName, queryDTO.getName());
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getType()), SystemDict::getType, queryDTO.getType());
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getRemark()), SystemDict::getRemark, queryDTO.getRemark());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<SystemDict>(page));
    }

    @ApiOperation(value = "查询所有字典数据", notes = "查询所有字典数据")
    @PostMapping("/list")
    public Result<List<SystemDict>> list() {
        return Result.ok(baseService.list(Wrappers.lambdaQuery(SystemDict.class)));
    }

    @ApiOperation(value = "批量删除字典数据", notes = "批量删除字典数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "idList", value = "删除id的list", required = true, dataType = "java.util.Set", paramType = "body")})
    @PostMapping("/removeByIdList")
    public Result removeByIdList(@NotNull(message = "删除的id集合不能为空") @RequestBody(required = false) Set<String> idList) {
        baseService.removeByIdList(idList);
        return Result.ok();
    }
}
