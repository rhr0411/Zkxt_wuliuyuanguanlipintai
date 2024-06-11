package com.geovis.manager.bs.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.entity.TbRiskHazardsStdDetail;
import com.geovis.manager.bs.service.ITbRiskHazardsStdDetailService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 风险隐患管理_排查标准_明细内容 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/bs/tbRiskHazardsStdDetail")
@RequiredArgsConstructor
@Api(value = "风险隐患管理_排查标准_明细内容接口", tags = "风险隐患管理_排查标准_明细内容接口")
@Slf4j
@Validated
public class TbRiskHazardsStdDetailController extends BaseController<ITbRiskHazardsStdDetailService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbRiskHazardsStdDetail>> getPage(@RequestBody @Validated PageParam<TbRiskHazardsStdDetail> pageParam) {
        IPage<TbRiskHazardsStdDetail> page = pageParam.buildPage();
        TbRiskHazardsStdDetail queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbRiskHazardsStdDetail> wrapper = Wrappers.lambdaQuery(TbRiskHazardsStdDetail.class).orderByDesc(TbRiskHazardsStdDetail::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getCheckDependent()), TbRiskHazardsStdDetail::getCheckDependent, queryDTO.getCheckDependent())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getContent()), TbRiskHazardsStdDetail::getContent, queryDTO.getContent())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getStdId()), TbRiskHazardsStdDetail::getStdId, queryDTO.getStdId())
            ;
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbRiskHazardsStdDetail>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbRiskHazardsStdDetail> saveOrUpdate(@RequestBody @Validated TbRiskHazardsStdDetail entity) {
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
