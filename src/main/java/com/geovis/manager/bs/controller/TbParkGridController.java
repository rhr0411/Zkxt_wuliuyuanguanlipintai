package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbParkGridSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbEnterprise;
import com.geovis.manager.bs.entity.TbParkGrid;
import com.geovis.manager.bs.service.ITbEnterpriseService;
import com.geovis.manager.bs.service.ITbParkGridService;
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
 * 园区网格 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-19
 */
@RestController
@RequestMapping("/bs/tbParkGrid")
@RequiredArgsConstructor
@Api(value = "园区网格接口", tags = "园区网格接口")
@Slf4j
@Validated
public class TbParkGridController extends BaseController<ITbParkGridService> {

    private final ITbEnterpriseService enterpriseService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbParkGrid>> getPage(@RequestBody @Validated PageParam<TbParkGrid> pageParam) {
        IPage<TbParkGrid> page = pageParam.buildPage();
        TbParkGrid queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbParkGrid> wrapper = Wrappers.lambdaQuery(TbParkGrid.class).orderByDesc(TbParkGrid::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getName()), TbParkGrid::getName, queryDTO.getName());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbParkGrid>(page));
    }

    @ApiOperation("列表查询")
    @ApiOperationSupport(order = 2)
    @PostMapping("/getList")
    public Result<List<TbParkGrid>> getList(@RequestBody @Validated TbParkGrid queryDTO) {
        LambdaQueryWrapper<TbParkGrid> wrapper = Wrappers.lambdaQuery(TbParkGrid.class).orderByDesc(TbParkGrid::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getName()), TbParkGrid::getName, queryDTO.getName());
        }
        return Result.ok(baseService.list(wrapper));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 3)
    @PostMapping("/saveOrUpdate")
    public Result<TbParkGrid> saveOrUpdate(@RequestBody @Validated TbParkGridSaveOrUpdateDTO dto) {
        TbParkGrid entity = BeanUtil.copyProperties(dto, TbParkGrid.class);
        baseService.saveOrUpdate(entity);
        // 清空企业所属的网格
        enterpriseService.update(Wrappers.lambdaUpdate(TbEnterprise.class)
                .set(TbEnterprise::getGridId, null)
                .eq(TbEnterprise::getGridId, entity.getId())
        );
        // 重新绑定
        if (CollUtil.isNotEmpty(dto.getEnterpriseIdList())) {
            enterpriseService.update(Wrappers.lambdaUpdate(TbEnterprise.class)
                    .set(TbEnterprise::getGridId, entity.getId())
                    .in(TbEnterprise::getId, dto.getEnterpriseIdList())
            );
        }
        return Result.ok(entity);
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 4)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        // 清空企业所属的网格
        enterpriseService.update(Wrappers.lambdaUpdate(TbEnterprise.class)
                .set(TbEnterprise::getGridId, null)
                .eq(TbEnterprise::getGridId, id)
        );
        return Result.ok();
    }

}
