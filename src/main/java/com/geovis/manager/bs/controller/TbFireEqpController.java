package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbFireEqpSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbFireEqp;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import com.geovis.manager.bs.service.ITbFireEqpService;
import com.geovis.common.core.controller.BaseController;

/**
 * <p>
 * 消防设施 前端控制器
 * </p>
*
* @author zengds
* @since 2024-04-15
*/
@RestController
@RequestMapping("/bs/tbFireEqp")
@RequiredArgsConstructor
@Api(value = "消防设施接口", tags = "消防设施接口")
@Slf4j
@Validated
public class TbFireEqpController extends BaseController<ITbFireEqpService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbFireEqp>> getPage(@RequestBody @Validated PageParam<TbFireEqp> pageParam) {
        IPage<TbFireEqp> page = pageParam.buildPage();
        TbFireEqp queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbFireEqp> wrapper = Wrappers.lambdaQuery(TbFireEqp.class).orderByDesc(TbFireEqp::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getName()), TbFireEqp::getName, queryDTO.getName())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbFireEqp::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbFireEqp>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbFireEqp> saveOrUpdate(@RequestBody @Validated TbFireEqpSaveOrUpdateDTO dto) {
        TbFireEqp entity = BeanUtil.copyProperties(dto, TbFireEqp.class);
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


    @ApiOperation("消防设施总数量")
    @ApiOperationSupport(order = 4)
    @PostMapping("/count")
    public Result<?> count() {
        return Result.ok(baseService.count());
    }
    
}
