package com.geovis.manager.bs.controller;


import com.geovis.common.auth.util.AuthUtil;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpDTO;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbParkGridPatrolTmp;
import com.geovis.manager.bs.service.ITbParkGridPatrolTmpService;
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
 * 园区网格_巡检设置 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/bs/tbParkGridPatrolTmp")
@RequiredArgsConstructor
@Api(value = "园区网格_巡检设置接口", tags = "园区网格_巡检设置接口")
@Slf4j
@Validated
public class TbParkGridPatrolTmpController extends BaseController<ITbParkGridPatrolTmpService> {

    @ApiOperation("列表查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getList")
    public Result<List<TbParkGridPatrolTmpDTO>> getList(@RequestBody @Validated TbParkGridPatrolTmp queryDTO) {
        return Result.ok(baseService.getList(queryDTO));
    }

    @ApiOperation("移动端-查询巡查队信息")
    @ApiOperationSupport(order = 2)
    @PostMapping("/getCurrentTmpDt")
    public Result<TbParkGridPatrolTmpDTO> getCurrentTmpDt() {
        return Result.ok(baseService.getTmpDetailByLeaderId(AuthUtil.getCurrentUserId()));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 3)
    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody @Validated TbParkGridPatrolTmpSaveOrUpdateDTO dto) {
        baseService.saveOrUpdateEntity(dto);
        return Result.ok();
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 4)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.deleteById(id);
        return Result.ok();
    }

}
