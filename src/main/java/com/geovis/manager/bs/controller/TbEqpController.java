package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbEqpQueryDTO;
import com.geovis.manager.bs.dto.TbEqpSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbEqp;
import com.geovis.manager.bs.service.ITbEqpService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 业务模块-设备表 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
@RestController
@RequestMapping("/bs/tbEqp")
@RequiredArgsConstructor
@Api(value = "业务模块-设备表接口", tags = "业务模块-设备表接口")
@Slf4j
@Validated
public class TbEqpController extends BaseController<ITbEqpService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbEqp>> getPage(@RequestBody @Validated PageParam<TbEqpQueryDTO> pageParam) {
        IPage<TbEqp> page = pageParam.buildPage();
        TbEqpQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbEqp> wrapper = Wrappers.lambdaQuery(TbEqp.class).orderByDesc(TbEqp::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getType()), TbEqp::getType, queryDTO.getType())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getStatus()), TbEqp::getStatus, queryDTO.getStatus())
                    .ge(ObjectUtil.isNotEmpty(queryDTO.getCheckDateStart()), TbEqp::getCheckDate, queryDTO.getCheckDateStart())
                    .le(ObjectUtil.isNotEmpty(queryDTO.getCheckDateEnd()), TbEqp::getCheckDate, queryDTO.getCheckDateEnd());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbEqp>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbEqp> saveOrUpdate(@RequestBody @Validated TbEqpSaveOrUpdateDTO dto) {
        TbEqp entity = BeanUtil.copyProperties(dto, TbEqp.class);
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
