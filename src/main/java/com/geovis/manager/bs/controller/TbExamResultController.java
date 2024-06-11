package com.geovis.manager.bs.controller;


import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbExamResultDTO;
import com.geovis.manager.bs.dto.TbExamResultQueryDTO;
import com.geovis.manager.bs.service.ITbExamResultService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 成绩管理和证书发放 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
@RestController
@RequestMapping("/bs/tbExamResult")
@RequiredArgsConstructor
@Api(value = "成绩管理和证书发放接口", tags = "成绩管理和证书发放接口")
@Slf4j
@Validated
public class TbExamResultController extends BaseController<ITbExamResultService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbExamResultDTO>> getPage(@RequestBody @Validated PageParam<TbExamResultQueryDTO> pageParam) {
        return Result.ok(baseService.getPage(pageParam));
    }

    @ApiOperation("移动端-最新考试成绩查询（电子证书也查询该接口）")
    @ApiOperationSupport(order = 2)
    @PostMapping("/getLastExamResult")
    public Result<TbExamResultDTO> getLastExamResult() {
        return Result.ok(baseService.getLastExamResult());
    }

}
