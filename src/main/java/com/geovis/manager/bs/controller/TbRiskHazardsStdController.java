package com.geovis.manager.bs.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbRiskHazardsStdTree;
import com.geovis.manager.bs.entity.TbRiskHazardsStd;
import com.geovis.manager.bs.entity.TbRiskHazardsStdDetail;
import com.geovis.manager.bs.service.ITbRiskHazardsStdDetailService;
import com.geovis.manager.bs.service.ITbRiskHazardsStdService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 风险隐患管理_排查标准 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/bs/tbRiskHazardsStd")
@RequiredArgsConstructor
@Api(value = "风险隐患管理_排查标准接口", tags = "风险隐患管理_排查标准接口")
@Slf4j
@Validated
public class TbRiskHazardsStdController extends BaseController<ITbRiskHazardsStdService> {

    private final ITbRiskHazardsStdDetailService riskHazardsStdDetailService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbRiskHazardsStd>> getPage(@RequestBody @Validated PageParam<TbRiskHazardsStd> pageParam) {
        IPage<TbRiskHazardsStd> page = pageParam.buildPage();
        TbRiskHazardsStd queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbRiskHazardsStd> wrapper = Wrappers.lambdaQuery(TbRiskHazardsStd.class).orderByDesc(TbRiskHazardsStd::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getName()), TbRiskHazardsStd::getName, queryDTO.getName())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getRemark()), TbRiskHazardsStd::getRemark, queryDTO.getRemark());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbRiskHazardsStd>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbRiskHazardsStd> saveOrUpdate(@RequestBody @Validated TbRiskHazardsStd entity) {
        baseService.saveOrUpdate(entity);
        return Result.ok(entity);
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 3)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        long count = baseService.count(Wrappers.lambdaQuery(TbRiskHazardsStd.class).eq(TbRiskHazardsStd::getParentId, id));
        Assert.isFalse(count > 0, "存在子节点，请先删除子节点数据");
        baseService.removeById(id);
        // 删除内容
        riskHazardsStdDetailService.remove(Wrappers.lambdaUpdate(TbRiskHazardsStdDetail.class).eq(TbRiskHazardsStdDetail::getStdId, id));
        return Result.ok();
    }


    @ApiOperation("树形查询")
    @ApiOperationSupport(order = 4)
    @PostMapping("/getTree")
    public Result<List<TbRiskHazardsStdTree>> getTree() {
       return Result.ok(baseService.getTree()) ;
    }

}
