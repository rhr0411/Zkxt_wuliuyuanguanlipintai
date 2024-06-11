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
import com.geovis.manager.bs.dto.TbDangerCraftSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbDangerCraft;
import com.geovis.manager.bs.entity.TbDangerSource;
import com.geovis.manager.bs.service.ITbDangerCraftService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 危险化工工艺 前端控制器
 * </p>
*
* @author zengds
* @since 2024-04-15
*/
@RestController
@RequestMapping("/bs/tbDangerCraft")
@RequiredArgsConstructor
@Api(value = "危险化工工艺接口", tags = "危险化工工艺接口")
@Slf4j
@Validated
public class TbDangerCraftController extends BaseController<ITbDangerCraftService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbDangerCraft>> getPage(@RequestBody @Validated PageParam<TbDangerCraft> pageParam) {
        IPage<TbDangerCraft> page = pageParam.buildPage();
        TbDangerCraft queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbDangerCraft> wrapper = Wrappers.lambdaQuery(TbDangerCraft.class).orderByDesc(TbDangerCraft::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getName()), TbDangerCraft::getName, queryDTO.getName())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbDangerCraft::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbDangerCraft>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbDangerCraft> saveOrUpdate(@RequestBody @Validated TbDangerCraftSaveOrUpdateDTO dto) {
        TbDangerCraft entity = BeanUtil.copyProperties(dto, TbDangerCraft.class);
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
