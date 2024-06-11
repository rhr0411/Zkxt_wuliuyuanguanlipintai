package com.geovis.manager.system.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.system.dto.SystemLogQueryDTO;
import com.geovis.manager.system.dto.SystemLogSaveDTO;
import com.geovis.manager.system.entity.SystemLog;
import com.geovis.manager.system.service.ISystemLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author 王响
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/system/systemLog")
@RequiredArgsConstructor
@Api(value = "系统管理模块-日志表相关接口", tags = "系统管理模块-日志表相关接口")
@Slf4j
@Validated
public class SystemLogController extends BaseController<ISystemLogService> {

    @ApiOperation(value = "保存日志信息", notes = "保存日志信息")
    @PostMapping({"/save" })
    public Result<String> save(@Validated @RequestBody SystemLogSaveDTO systemLogSaveDTO) {
        baseService.syncSave(systemLogSaveDTO);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询信息", notes = "分页查询信息")
    @PostMapping("/listPage")
    public Result<PageResult<SystemLog>> listPage(@RequestBody @Validated PageParam<SystemLogQueryDTO> pageParam) {
        return Result.ok(baseService.listPage(pageParam));
    }

    @ApiOperation(value = "通过线程ID查询", notes = "通过线程ID查询")
    @GetMapping("/getByThreadId/{threadId}")
    public Result<SystemLog> getByThreadId(@PathVariable("threadId") String threadId) {
        return Result.ok(baseService.getOne(Wrappers.lambdaQuery(SystemLog.class).eq(SystemLog::getThreadId, threadId)));
    }

}
