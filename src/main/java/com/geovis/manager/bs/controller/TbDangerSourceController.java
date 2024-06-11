package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbDangerSourceSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbDangerSource;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import com.geovis.manager.bs.service.ITbDangerSourceService;
import com.geovis.common.core.controller.BaseController;

/**
 * <p>
 * 重大危险源 前端控制器
 * </p>
*
* @author zengds
* @since 2024-04-15
*/
@RestController
@RequestMapping("/bs/tbDangerSource")
@RequiredArgsConstructor
@Api(value = "重大危险源接口", tags = "重大危险源接口")
@Slf4j
@Validated
public class TbDangerSourceController extends BaseController<ITbDangerSourceService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbDangerSource>> getPage(@RequestBody @Validated PageParam<TbDangerSource> pageParam) {
        IPage<TbDangerSource> page = pageParam.buildPage();
        TbDangerSource queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbDangerSource> wrapper = Wrappers.lambdaQuery(TbDangerSource.class).orderByDesc(TbDangerSource::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getName()), TbDangerSource::getName, queryDTO.getName())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getSource()), TbDangerSource::getSource, queryDTO.getSource())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbDangerSource::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbDangerSource>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbDangerSource> saveOrUpdate(@RequestBody @Validated TbDangerSourceSaveOrUpdateDTO dto) {
        TbDangerSource entity = BeanUtil.copyProperties(dto, TbDangerSource.class);
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
