package com.geovis.manager.bs.controller;


import com.geovis.common.auth.util.AuthUtil;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbExamResultDTO;
import com.geovis.manager.bs.dto.TbExamResultQueryDTO;
import com.geovis.manager.bs.dto.TbExamStatisticDTO;
import com.geovis.manager.bs.entity.TbExamRecord;
import com.geovis.manager.bs.entity.TbExamResult;
import com.geovis.manager.bs.service.ITbExamRecordService;
import com.geovis.manager.bs.service.ITbExamResultService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

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


    @Autowired
    private ITbExamRecordService iTbExamRecordService;


    @Autowired
    private RedisTemplate redisTemplate;

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

    @ApiOperation("数据统计")
    @ApiOperationSupport(order = 3)
    @GetMapping("/statistic")
    public Result<TbExamStatisticDTO> statistic() {


        return Result.ok(iTbExamRecordService.statistic());
    }


    @ApiOperation("提醒")
    @ApiOperationSupport(order = 4)
    @PostMapping("/notifySend/{userId}")
    public Result notifyMessage(@PathVariable(value = "userId") String UserId) {
        redisTemplate.opsForHash().putIfAbsent(UserId,"notify" , "证书即将过期");

        return Result.ok();
    }


    @ApiOperation("接受提醒")
    @ApiOperationSupport(order = 5)
    @PostMapping("/notifyGet")
    public Result notifyMessage() {
        if(redisTemplate.opsForHash().hasKey(AuthUtil.getCurrentUserId(),"notify")) {
            Object o = redisTemplate.opsForHash().get(AuthUtil.getCurrentUserId(),"notify");
            redisTemplate.opsForHash().delete(AuthUtil.getCurrentUserId(),"notify");
            return Result.ok(o);
        }

        return Result.ok();
    }



}
