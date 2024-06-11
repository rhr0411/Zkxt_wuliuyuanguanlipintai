package com.geovis.manager.bs.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbEqpCheckDTO;
import com.geovis.manager.bs.dto.TbEqpCheckQueryDTO;
import com.geovis.manager.bs.dto.TbEqpCheckSaveOrUpdateDTO;
import com.geovis.manager.bs.dto.TbEqpCheckStatisticDTO;
import com.geovis.manager.bs.entity.TbEqpCheck;
import com.geovis.manager.bs.service.ITbEqpCheckService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 装置开停车和大检修管理 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@RestController
@RequestMapping("/bs/tbEqpCheck")
@RequiredArgsConstructor
@Api(value = "装置开停车和大检修管理接口", tags = "装置开停车和大检修管理接口")
@Slf4j
@Validated
public class TbEqpCheckController extends BaseController<ITbEqpCheckService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbEqpCheck>> getPage(@RequestBody @Validated PageParam<TbEqpCheckQueryDTO> pageParam) {
        IPage<TbEqpCheck> page = pageParam.buildPage();
        TbEqpCheckQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbEqpCheck> wrapper = Wrappers.lambdaQuery(TbEqpCheck.class).orderByDesc(TbEqpCheck::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getType()), TbEqpCheck::getType, queryDTO.getType())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getCheckAddress()), TbEqpCheck::getCheckAddress, queryDTO.getCheckAddress())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbEqpCheck::getEnterpriseId, queryDTO.getEnterpriseId());
            if (ObjectUtil.isNotEmpty(queryDTO.getCheckTimeStart()) && ObjectUtil.isEmpty(queryDTO.getCheckTimeEnd())) {
                wrapper.and(wp -> wp.ge(TbEqpCheck::getCheckTimeStart, queryDTO.getCheckTimeStart()).le(TbEqpCheck::getCheckTimeEnd, queryDTO.getCheckTimeStart()));
            }
            if (ObjectUtil.isNotEmpty(queryDTO.getCheckTimeEnd()) && ObjectUtil.isEmpty(queryDTO.getCheckTimeStart())) {
                wrapper.and(wp -> wp.ge(TbEqpCheck::getCheckTimeStart, queryDTO.getCheckTimeEnd()).le(TbEqpCheck::getCheckTimeEnd, queryDTO.getCheckTimeEnd()));
            }
            if (ObjectUtil.isNotEmpty(queryDTO.getCheckTimeStart()) && ObjectUtil.isNotEmpty(queryDTO.getCheckTimeEnd())) {
                wrapper.and(wp -> wp.ge(TbEqpCheck::getCheckTimeStart, queryDTO.getCheckTimeStart()).le(TbEqpCheck::getCheckTimeEnd, queryDTO.getCheckTimeStart())
                        .or()
                        .ge(TbEqpCheck::getCheckTimeStart, queryDTO.getCheckTimeEnd()).le(TbEqpCheck::getCheckTimeEnd, queryDTO.getCheckTimeEnd()));
            }
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbEqpCheck>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbEqpCheck> saveOrUpdate(@RequestBody @Validated TbEqpCheckSaveOrUpdateDTO dto) {
        TbEqpCheck entity = baseService.saveOrUpdateEntity(dto);
        return Result.ok(entity);
    }

    @ApiOperation("明细查询")
    @ApiOperationSupport(order = 2)
    @GetMapping("/getEntityById/{id}")
    public Result<TbEqpCheckDTO> getEntityById(@PathVariable("id") String id) {
        TbEqpCheckDTO dto = baseService.getEntityById(id);
        return Result.ok(dto);
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 3)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("统计")
    @ApiOperationSupport(order = 2)
    @GetMapping("/statistic")
    public Result<TbEqpCheckStatisticDTO> statistic() {
        TbEqpCheckStatisticDTO dto = baseService.statistic();
        return Result.ok(dto);
    }

}
