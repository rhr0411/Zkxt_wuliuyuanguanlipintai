package com.geovis.manager.bs.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.entity.TbMessage;
import com.geovis.manager.bs.service.ITbMessageService;
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
 * 消息提醒 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-05-06
 */
@RestController
@RequestMapping("/bs/tbMessage")
@RequiredArgsConstructor
@Api(value = "消息提醒接口", tags = "消息提醒接口")
@Slf4j
@Validated
public class TbMessageController extends BaseController<ITbMessageService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbMessage>> getPage(@RequestBody @Validated PageParam<TbMessage> pageParam) {
        IPage<TbMessage> page = pageParam.buildPage();
        TbMessage queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbMessage> wrapper = Wrappers.lambdaQuery(TbMessage.class).orderByDesc(TbMessage::getUpdateTime).orderByDesc(TbMessage::getCreateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbMessage::getEnterpriseId, queryDTO.getEnterpriseId())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getType()), TbMessage::getType, queryDTO.getType())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getContent()), TbMessage::getContent, queryDTO.getContent());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbMessage>(page));
    }

}
