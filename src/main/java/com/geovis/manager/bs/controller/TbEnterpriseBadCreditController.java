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
import com.geovis.manager.bs.dto.TbEnterpriseBadCreditDTO;
import com.geovis.manager.bs.dto.TbEnterpriseBadCreditQueryDTO;
import com.geovis.manager.bs.dto.TbEnterpriseBadCreditSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbEnterpriseBadCredit;
import com.geovis.manager.bs.service.ITbEnterpriseBadCreditService;
import com.geovis.manager.bs.service.ITbEnterpriseService;
import com.geovis.manager.system.dto.SystemFileQueryDTO;
import com.geovis.manager.system.entity.SystemFileBusiness;
import com.geovis.manager.system.service.ISystemFileBusinessService;
import com.geovis.manager.system.service.ISystemFileService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 企业不良信用管理 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@RestController
@RequestMapping("/bs/tbEnterpriseBadCredit")
@RequiredArgsConstructor
@Api(value = "企业不良信用管理接口", tags = "企业不良信用管理接口")
@Slf4j
@Validated
public class TbEnterpriseBadCreditController extends BaseController<ITbEnterpriseBadCreditService> {

    private final ITbEnterpriseService enterpriseService;

    private final ISystemFileBusinessService systemFileBusinessService;

    private final ISystemFileService systemFileService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbEnterpriseBadCredit>> getPage(@RequestBody @Validated PageParam<TbEnterpriseBadCreditQueryDTO> pageParam) {
        IPage<TbEnterpriseBadCredit> page = pageParam.buildPage();
        TbEnterpriseBadCreditQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbEnterpriseBadCredit> wrapper = Wrappers.lambdaQuery(TbEnterpriseBadCredit.class).orderByDesc(TbEnterpriseBadCredit::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.ge(ObjectUtil.isNotEmpty(queryDTO.getStartCreateTime()), TbEnterpriseBadCredit::getCreateTime, queryDTO.getStartCreateTime())
                    .le(ObjectUtil.isNotEmpty(queryDTO.getEndCreateTime()), TbEnterpriseBadCredit::getCreateTime, queryDTO.getEndCreateTime())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbEnterpriseBadCredit::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbEnterpriseBadCredit>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbEnterpriseBadCredit> saveOrUpdate(@RequestBody @Validated TbEnterpriseBadCreditSaveOrUpdateDTO dto) {
        TbEnterpriseBadCredit entity = BeanUtil.copyProperties(dto, TbEnterpriseBadCredit.class);
        entity.setEnterpriseName(enterpriseService.getById(entity.getEnterpriseId()).getName());
        baseService.saveOrUpdate(entity);
        // 附件处理
        systemFileBusinessService.remove(Wrappers.lambdaUpdate(SystemFileBusiness.class).eq(SystemFileBusiness::getParam1, entity.getId()));
        if (CollUtil.isNotEmpty(dto.getFileIdList())) {
            dto.getFileIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(entity.getId());
                systemFileBusinessService.save(business);
            });
        }
        return Result.ok(entity);
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 3)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        return Result.ok();
    }


    @ApiOperation("根据ID查询企业详情")
    @ApiOperationSupport(order = 4)
    @PostMapping("/getById/{id}")
    public Result<TbEnterpriseBadCreditDTO> getById(@PathVariable("id") String id) {
        TbEnterpriseBadCredit entity = baseService.getById(id);
        TbEnterpriseBadCreditDTO dto = null;
        if (ObjectUtil.isNotEmpty(entity)) {
            dto = BeanUtil.copyProperties(entity, TbEnterpriseBadCreditDTO.class);
            // 查询附件列表
            SystemFileQueryDTO fileQueryDTO = new SystemFileQueryDTO();
            fileQueryDTO.setParam1(entity.getId());
            dto.setFileList(systemFileService.getList(fileQueryDTO));
        }
        return Result.ok(dto);
    }

}
