package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbResponsibilityQueryDTO;
import com.geovis.manager.bs.dto.TbResponsibilitySaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbResponsibility;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import com.geovis.manager.bs.service.ITbResponsibilityService;
import com.geovis.common.core.controller.BaseController;

/**
 * <p>
 * 责任清单管理 前端控制器
 * </p>
*
* @author zengds
* @since 2024-04-15
*/
@RestController
@RequestMapping("/bs/tbResponsibility")
@RequiredArgsConstructor
@Api(value = "责任清单管理接口", tags = "责任清单管理接口")
@Slf4j
@Validated
public class TbResponsibilityController extends BaseController<ITbResponsibilityService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbResponsibility>> getPage(@RequestBody @Validated PageParam<TbResponsibilityQueryDTO> pageParam) {
        return Result.ok(baseService.getResponsibilityPage(pageParam));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbResponsibility> saveOrUpdate(@RequestBody @Validated TbResponsibilitySaveOrUpdateDTO dto) {
        TbResponsibility entity = BeanUtil.copyProperties(dto, TbResponsibility.class);
        baseService.saveOrUpdate(entity);
        return Result.ok(entity);
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 3)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        return Result.ok();
    }

}
