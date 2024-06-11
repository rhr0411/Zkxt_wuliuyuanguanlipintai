package com.geovis.manager.bs.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.auth.util.AuthUtil;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.core.controller.BaseController;
import com.geovis.manager.bs.dto.TbExamEndAnswerDTO;
import com.geovis.manager.bs.dto.TbExamEndResultDTO;
import com.geovis.manager.bs.entity.TbExamQuestionRecord;
import com.geovis.manager.bs.entity.TbExamRecord;
import com.geovis.manager.bs.service.ITbExamRecordService;
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

import java.util.List;

/**
 * <p>
 * 考试记录 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
@RestController
@RequestMapping("/bs/tbExamRecord")
@RequiredArgsConstructor
@Api(value = "考试记录接口", tags = "考试记录接口")
@Slf4j
@Validated
public class TbExamRecordController extends BaseController<ITbExamRecordService> {

    @ApiOperation("移动端-考试成绩查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getMyExamRecordList")
    public Result<List<TbExamRecord>> getMyExamRecordList() {
        List<TbExamRecord> list = baseService.list(Wrappers.lambdaQuery(TbExamRecord.class)
                .eq(TbExamRecord::getUserId, AuthUtil.getCurrentUserId())
                .eq(TbExamRecord::getStatus, CommonConstants.YES)
                .orderByDesc(TbExamRecord::getExamDate)
        );
        return Result.ok(list);
    }

    @ApiOperation("移动端-开始考试")
    @ApiOperationSupport(order = 2)
    @PostMapping("/beginExam")
    public Result<?> beginExam() {
        List<TbExamQuestionRecord> list = baseService.beginExam(AuthUtil.getCurrentUserId());
        return Result.ok(list);
    }

    @ApiOperation("移动端-交卷")
    @ApiOperationSupport(order = 2)
    @PostMapping("/endExam")
    public Result<TbExamEndResultDTO> endExam(@Validated @RequestBody TbExamEndAnswerDTO dto) {
        TbExamEndResultDTO record = baseService.endExam(dto);
        return Result.ok(record);
    }

}
