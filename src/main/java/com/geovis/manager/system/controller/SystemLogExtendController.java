package com.geovis.manager.system.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.manager.system.entity.SystemLogExtend;
import com.geovis.manager.system.service.ISystemLogExtendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author 王响
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/system/systemLogExtend")
@RequiredArgsConstructor
@Api(value = "系统管理模块-日志表相关接口", tags = "系统管理模块-日志表相关接口")
@Slf4j
@Validated
public class SystemLogExtendController extends BaseController<ISystemLogExtendService> {

    @ApiOperation(value = "根据日志ID查询信息", notes = "根据日志ID查询信息")
    @GetMapping({"/getByLogId/{logId}"})
    public Result<SystemLogExtend> getByLogId(@PathVariable("logId") String logId) {
        return Result.ok(baseService.getOne(Wrappers.lambdaQuery(SystemLogExtend.class).eq(SystemLogExtend::getLogId, logId)));
    }

}
