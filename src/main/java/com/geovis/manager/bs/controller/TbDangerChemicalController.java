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
import com.geovis.manager.bs.dto.TbDangerChemicalSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbDangerChemical;
import com.geovis.manager.bs.service.ITbDangerChemicalService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 危险化学品 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@RestController
@RequestMapping("/bs/tbDangerChemical")
@RequiredArgsConstructor
@Api(value = "危险化学品接口", tags = "危险化学品接口")
@Slf4j
@Validated
public class TbDangerChemicalController extends BaseController<ITbDangerChemicalService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbDangerChemical>> getPage(@RequestBody @Validated PageParam<TbDangerChemical> pageParam) {
        IPage<TbDangerChemical> page = pageParam.buildPage();
        TbDangerChemical queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbDangerChemical> wrapper = Wrappers.lambdaQuery(TbDangerChemical.class).orderByDesc(TbDangerChemical::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getName()), TbDangerChemical::getName, queryDTO.getName())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getType()), TbDangerChemical::getType, queryDTO.getType())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbDangerChemical::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbDangerChemical>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbDangerChemical> saveOrUpdate(@RequestBody @Validated TbDangerChemicalSaveOrUpdateDTO dto) {
        TbDangerChemical entity = BeanUtil.copyProperties(dto, TbDangerChemical.class);
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



    @ApiOperation("危险化学总数量")
    @ApiOperationSupport(order = 4)
    @PostMapping("/count")
    public Result<?> deletcounteById() {
        return Result.ok(baseService.count());
    }

}
