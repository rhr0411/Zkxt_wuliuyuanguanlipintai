package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbAccidentDTO;
import com.geovis.manager.bs.dto.TbAccidentSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbAccident;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import com.geovis.manager.bs.service.ITbAccidentService;
import com.geovis.common.core.controller.BaseController;

/**
 * <p>
 * 事故事件管理 前端控制器
 * </p>
*
* @author zengds
* @since 2024-04-15
*/
@RestController
@RequestMapping("/bs/tbAccident")
@RequiredArgsConstructor
@Api(value = "事故事件管理接口", tags = "事故事件管理接口")
@Slf4j
@Validated
public class TbAccidentController extends BaseController<ITbAccidentService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbAccident>> getPage(@RequestBody @Validated PageParam<TbAccident> pageParam) {
        IPage<TbAccident> page = pageParam.buildPage();
        TbAccident queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbAccident> wrapper = Wrappers.lambdaQuery(TbAccident.class).orderByDesc(TbAccident::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getType()), TbAccident::getType, queryDTO.getType())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbAccident::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbAccident>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbAccident> saveOrUpdate(@RequestBody @Validated TbAccidentSaveOrUpdateDTO dto) {
        return Result.ok(baseService.saveAccident(dto));
    }

    @ApiOperation("查询详情")
    @ApiOperationSupport(order = 2)
    @PostMapping("/getById/{id}")
    public Result<TbAccidentDTO> getById(@PathVariable("id") String id) {
        return Result.ok(baseService.getAccidentById(id));
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 3)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        return Result.ok();
    }
    
}
