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
import com.geovis.manager.bs.dto.TbCommitmentEveryDaySaveDTO;
import com.geovis.manager.bs.dto.TbCommitmentQueryDTO;
import com.geovis.manager.bs.entity.TbCommitment;
import com.geovis.manager.bs.entity.TbCommitmentEveryDay;
import com.geovis.manager.bs.service.ITbCommitmentEveryDayService;
import com.geovis.manager.bs.service.ITbCommitmentService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 安全承诺书 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@RestController
@RequestMapping("/bs/tbCommitment")
@RequiredArgsConstructor
@Api(value = "安全承诺书接口", tags = "安全承诺书接口")
@Slf4j
@Validated
public class TbCommitmentController extends BaseController<ITbCommitmentService> {

    private final ITbCommitmentEveryDayService commitmentEveryDayService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbCommitment>> getPage(@RequestBody @Validated PageParam<TbCommitmentQueryDTO> pageParam) {
        IPage<TbCommitment> page = pageParam.buildPage();
        TbCommitmentQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbCommitment> wrapper = Wrappers.lambdaQuery(TbCommitment.class).orderByDesc(TbCommitment::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getType()), TbCommitment::getType, queryDTO.getType())
                    .ge(ObjectUtil.isNotEmpty(queryDTO.getCreateTimeStart()), TbCommitment::getCreateTime, queryDTO.getCreateTimeStart())
                    .le(ObjectUtil.isNotEmpty(queryDTO.getCreateTimeEnd()), TbCommitment::getCreateTime, queryDTO.getCreateTimeEnd())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbCommitment::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbCommitment>(page));
    }

    @ApiOperation("根据承诺ID查询每日承诺详情")
    @ApiOperationSupport(order = 2)
    @PostMapping("/getEveryDayByCommitId/{id}")
    public Result<TbCommitmentEveryDay> getEveryDayDetailById(@PathVariable("id") String id) {
        return Result.ok(commitmentEveryDayService.getOne(Wrappers.lambdaQuery(TbCommitmentEveryDay.class).eq(TbCommitmentEveryDay::getCommitId, id)));
    }

    @ApiOperation("保存或更新-年终、年度履诺报告")
    @ApiOperationSupport(order = 3)
    @PostMapping("/saveOrUpdate")
    public Result<TbCommitment> saveOrUpdate(@RequestBody @Validated TbCommitment entity) {
        baseService.saveOrUpdate(entity);
        return Result.ok(entity);
    }


    @ApiOperation("保存或更新-每日安全承诺")
    @ApiOperationSupport(order = 4)
    @PostMapping("/saveOrUpdateEveryDay")
    public Result<TbCommitment> saveOrUpdateEveryDay(@RequestBody @Validated TbCommitmentEveryDaySaveDTO dto) {
        TbCommitment entity = BeanUtil.copyProperties(dto, TbCommitment.class);
        baseService.saveOrUpdate(entity);
        commitmentEveryDayService.remove(Wrappers.lambdaUpdate(TbCommitmentEveryDay.class).eq(TbCommitmentEveryDay::getCommitId, entity.getId()));
        commitmentEveryDayService.save(BeanUtil.copyProperties(dto, TbCommitmentEveryDay.class));
        return Result.ok(entity);
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 5)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        commitmentEveryDayService.remove(Wrappers.lambdaUpdate(TbCommitmentEveryDay.class).eq(TbCommitmentEveryDay::getCommitId, id));
        return Result.ok();
    }

}
