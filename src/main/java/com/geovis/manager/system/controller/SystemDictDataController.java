package com.geovis.manager.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.core.controller.BaseController;
import com.geovis.manager.system.dto.SystemDictDataQueryDTO;
import com.geovis.manager.system.dto.SystemDictDataSaveDTO;
import com.geovis.manager.system.dto.SystemDictDataUpdateDTO;
import com.geovis.manager.system.entity.SystemDictData;
import com.geovis.manager.system.service.ISystemDictDataService;
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
 * 字典数据表 前端控制器
 * </p>
 *
 * @author 王响
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/system/systemDictData")
@AllArgsConstructor
@Api(value = "系统管理模块-字典数据相关接口", tags = "系统管理模块-字典数据相关接口")
@Slf4j
@Validated
public class SystemDictDataController extends BaseController<ISystemDictDataService> {


    @ApiOperation(value = "保存字典数据信息", notes = "保存字典数据信息")
    @PostMapping({"/save"})
    public Result save(@Validated @RequestBody SystemDictDataSaveDTO saveDTO) {

        LambdaQueryWrapper<SystemDictData> wrapper = Wrappers.lambdaQuery(SystemDictData.class)
                .eq(SystemDictData::getDictId, saveDTO.getDictId())
                .eq(SystemDictData::getDataKey, saveDTO.getDataKey());

        Long count = baseService.count(wrapper);
        if (count > 0) {
            return Result.failed(StrUtil.format("该编码【{}】在该字典类型已被使用！", saveDTO.getDataKey()));
        }

        SystemDictData systemDictData = BeanUtil.toBean(saveDTO, SystemDictData.class);
        systemDictData.setDataStatus(CommonConstants.YES);
        baseService.save(systemDictData);
        return Result.ok();
    }

    @ApiOperation(value = "修改字典数据信息", notes = "修改字典数据信息")
    @PostMapping({"/update"})
    public Result update(@Validated @RequestBody SystemDictDataUpdateDTO updateDTO) {

        LambdaQueryWrapper<SystemDictData> wrapper = Wrappers.lambdaQuery(SystemDictData.class)
                .eq(SystemDictData::getDictId, updateDTO.getDictId())
                .eq(SystemDictData::getDataKey, updateDTO.getDataKey())
                .ne(SystemDictData::getId, updateDTO.getId());

        Long count = baseService.count(wrapper);
        if (count > 0) {
            return Result.failed(StrUtil.format("该编码【{}】在该字典类型已被使用！", updateDTO.getDataKey()));
        }
        SystemDictData systemDictData = BeanUtil.toBean(updateDTO, SystemDictData.class);
        baseService.updateById(systemDictData);
        return Result.ok();
    }

    @ApiOperation(value = "查询所有字典数据", notes = "查询所有字典数据")
    @PostMapping("/list")
    public Result<List<SystemDictData>> list(@RequestBody @Validated SystemDictDataQueryDTO queryDTO) {
        LambdaQueryWrapper<SystemDictData> wrapper = Wrappers.lambdaQuery(SystemDictData.class).orderByAsc(SystemDictData::getIdx);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getDictId()), SystemDictData::getDictId, queryDTO.getDictId());
        }
        return Result.ok(baseService.list(wrapper));
    }

    @ApiOperation(value = "批量删除字典数据", notes = "批量删除字典数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "idList", value = "删除id的list", required = true, dataType = "java.util.Set", paramType = "body")})
    @PostMapping("/removeByIdList")
    public Result removeByIdList(@NotNull(message = "删除的id集合不能为空") @RequestBody(required = false) Set<String> idList) {
        baseService.removeByIds(idList);
        return Result.ok();
    }

}
