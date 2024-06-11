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
import com.geovis.manager.bs.dto.TbSafeEnviroManagerQueryDTO;
import com.geovis.manager.bs.dto.TbSafeEnviroManagerSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbSafeEnviroManager;
import com.geovis.manager.bs.service.ITbSafeEnviroManagerService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 业务模块-安全与环境管理信息表 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
@RestController
@RequestMapping("/bs/tbSafeEnviroManager")
@RequiredArgsConstructor
@Api(value = "业务模块-安全与环境管理信息表接口", tags = "业务模块-安全与环境管理信息表接口")
@Slf4j
@Validated
public class TbSafeEnviroManagerController extends BaseController<ITbSafeEnviroManagerService> {


    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbSafeEnviroManager>> getPage(@RequestBody @Validated PageParam<TbSafeEnviroManagerQueryDTO> pageParam) {
        IPage<TbSafeEnviroManager> page = pageParam.buildPage();
        TbSafeEnviroManagerQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbSafeEnviroManager> wrapper = Wrappers.lambdaQuery(TbSafeEnviroManager.class).orderByDesc(TbSafeEnviroManager::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getType()), TbSafeEnviroManager::getType, queryDTO.getType())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getFileName()), TbSafeEnviroManager::getFileName, queryDTO.getFileName())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbSafeEnviroManager::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbSafeEnviroManager>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbSafeEnviroManager> saveOrUpdate(@RequestBody @Validated TbSafeEnviroManagerSaveOrUpdateDTO dto) {
        TbSafeEnviroManager entity = BeanUtil.copyProperties(dto, TbSafeEnviroManager.class);
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
