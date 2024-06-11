package com.geovis.manager.system.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.system.dto.SystemLogQueryDTO;
import com.geovis.manager.system.dto.SystemLogSaveDTO;
import com.geovis.manager.system.entity.SystemLog;
import com.geovis.manager.system.entity.SystemLogExtend;
import com.geovis.manager.system.mapper.SystemLogMapper;
import com.geovis.manager.system.service.ISystemLogExtendService;
import com.geovis.manager.system.service.ISystemLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2021-01-25
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements ISystemLogService {

    public static final Integer maxLength = 2000;

    private final ISystemLogExtendService systemLogExtendService;

    @Async
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void syncSave(SystemLogSaveDTO saveDTO) {
        if (ObjectUtil.isEmpty(saveDTO)) {
            log.error("日志信息为空，不能入库");
            return;
        }
        SystemLog systemLog = BeanUtil.toBean(saveDTO, SystemLog.class);
        if (StrUtil.isNotEmpty(saveDTO.getRequestReturn()) && saveDTO.getRequestReturn().length() > maxLength) {
            systemLog.setRequestReturn(saveDTO.getRequestReturn().substring(0, maxLength));
        }
        if (StrUtil.isNotEmpty(saveDTO.getExceptionMessage()) && saveDTO.getExceptionMessage().length() > maxLength) {
            systemLog.setRequestReturn(saveDTO.getExceptionMessage().substring(0, maxLength));
        }
        save(systemLog);
        SystemLogExtend extend = new SystemLogExtend();
        extend.setLogId(systemLog.getId())
                .setLogException(saveDTO.getExceptionMessage())
                .setLogReturn(saveDTO.getRequestReturn());
        systemLogExtendService.save(extend);
    }

    @Override
    public PageResult<SystemLog> listPage(PageParam<SystemLogQueryDTO> pageParam) {
        SystemLogQueryDTO queryDTO = pageParam.getQuery();
        IPage<SystemLog> page = pageParam.buildPage();

        final LambdaQueryWrapper<SystemLog> queryWrapper = Wrappers.lambdaQuery(SystemLog.class).orderByDesc(SystemLog::getBeginRequestTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            queryWrapper.eq(StrUtil.isNotEmpty(queryDTO.getRequestType()), SystemLog::getRequestType, queryDTO.getRequestType())
                    .eq(StrUtil.isNotEmpty(queryDTO.getLogLevel()), SystemLog::getLogLevel, queryDTO.getLogLevel())
                    .like(StrUtil.isNotEmpty(queryDTO.getRequestUri()), SystemLog::getRequestUri, queryDTO.getRequestUri())
                    .like(StrUtil.isNotEmpty(queryDTO.getRequestParams()), SystemLog::getRequestParams, queryDTO.getRequestParams())
                    .like(StrUtil.isNotEmpty(queryDTO.getRequestReturn()), SystemLog::getRequestReturn, queryDTO.getRequestReturn())
                    .like(StrUtil.isNotEmpty(queryDTO.getExceptionClass()), SystemLog::getExceptionClass, queryDTO.getExceptionClass())
                    .like(StrUtil.isNotEmpty(queryDTO.getExceptionMessage()), SystemLog::getExceptionMessage, queryDTO.getExceptionMessage())
                    .like(StrUtil.isNotEmpty(queryDTO.getLogDescription()), SystemLog::getLogDescription, queryDTO.getLogDescription())
                    .like(StrUtil.isNotEmpty(queryDTO.getCreateBy()), SystemLog::getCreateBy, queryDTO.getCreateBy())
                    .ge(ObjectUtil.isNotEmpty(queryDTO.getSpendTime()), SystemLog::getSpendTime, queryDTO.getSpendTime())
                    .ge(ObjectUtil.isNotEmpty(queryDTO.getBeginRequestTimeStart()), SystemLog::getBeginRequestTime, queryDTO.getBeginRequestTimeStart())
                    .le(ObjectUtil.isNotEmpty(queryDTO.getBeginRequestTimeEnd()), SystemLog::getBeginRequestTime, queryDTO.getBeginRequestTimeEnd())
            ;
        }
        baseMapper.selectPage(page, queryWrapper);
        return new PageResult<SystemLog>(page);
    }

}
