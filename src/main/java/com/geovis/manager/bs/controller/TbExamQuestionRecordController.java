package com.geovis.manager.bs.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.manager.bs.entity.TbExamQuestionRecord;
import com.geovis.manager.bs.service.ITbExamQuestionRecordService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 考生答题记录 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
@RestController
@RequestMapping("/bs/tbExamQuestionRecord")
@RequiredArgsConstructor
@Api(value = "考生答题记录接口", tags = "考生答题记录接口")
@Slf4j
@Validated
public class TbExamQuestionRecordController extends BaseController<ITbExamQuestionRecordService> {

    @ApiOperation("考生答题列表查询")
    @ApiOperationSupport(order = 1)
    @GetMapping("/getList/{examRecordId}")
    public Result<List<TbExamQuestionRecord>> getList(@PathVariable("examRecordId") String examRecordId) {
        return Result.ok(baseService.list(Wrappers.lambdaQuery(TbExamQuestionRecord.class)
                .eq(TbExamQuestionRecord::getExamRecordId, examRecordId)
                .orderByAsc(TbExamQuestionRecord::getSortIndex))
        );
    }

}
