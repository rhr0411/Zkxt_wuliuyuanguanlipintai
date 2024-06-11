package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.constant.ThirdUnitWorkerType;
import com.geovis.manager.bs.dto.TbExamQuestionDTO;
import com.geovis.manager.bs.dto.TbExamQuestionQueryDTO;
import com.geovis.manager.bs.dto.TbExamQuestionSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbExamQuestion;
import com.geovis.manager.bs.entity.TbExamQuestionWorkType;
import com.geovis.manager.bs.mapper.TbExamQuestionMapper;
import com.geovis.manager.bs.service.ITbExamQuestionService;
import com.geovis.manager.bs.service.ITbExamQuestionWorkTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 考试题库 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbExamQuestionServiceImpl extends ServiceImpl<TbExamQuestionMapper, TbExamQuestion> implements ITbExamQuestionService {

    private final ITbExamQuestionWorkTypeService examQuestionWorkTypeService;

    @Override
    public PageResult<TbExamQuestionDTO> getPage(PageParam<TbExamQuestionQueryDTO> pageParam) {
        IPage<TbExamQuestionDTO> page = pageParam.buildPage();
        baseMapper.selectDataList(page, getObjectQueryWrapper(pageParam.getQuery()));
        // 工种转换
        page.getRecords().forEach(item -> {
            item.setWorkerType(ThirdUnitWorkerType.getWorkerTypeText(item.getWorkerType()));
        });
        return new PageResult<>(page);
    }

    @NotNull
    private QueryWrapper<Object> getObjectQueryWrapper(TbExamQuestionQueryDTO queryDTO) {
        QueryWrapper<Object> queryWrapper = Wrappers.query();
        queryWrapper.eq("1",1).orderByDesc("a.update_time");
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            queryWrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getExamType()), "a.exam_type", queryDTO.getExamType())
                    .exists(ObjectUtil.isNotEmpty(queryDTO.getWorkType()), "select 1 from tb_exam_question_work_type c where c.exam_question_id = a.id and c.work_type = '" + queryDTO.getWorkType() + "'");
        }
        return queryWrapper;
    }

    @Override
    public List<TbExamQuestionDTO> getList(TbExamQuestionQueryDTO queryDTO) {
        List<TbExamQuestionDTO> list = baseMapper.selectDataList(getObjectQueryWrapper(queryDTO));
        list.forEach(item -> {
            item.setWorkerType(ThirdUnitWorkerType.getWorkerTypeText(item.getWorkerType()));
        });
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateEntity(TbExamQuestionSaveOrUpdateDTO dto) {
        TbExamQuestion entity = BeanUtil.copyProperties(dto, TbExamQuestion.class);
        saveOrUpdate(entity);
        // 工种
        examQuestionWorkTypeService.remove(Wrappers.lambdaUpdate(TbExamQuestionWorkType.class).eq(TbExamQuestionWorkType::getExamQuestionId, entity.getId()));
        String[] split = dto.getWorkType().split(StrUtil.COMMA);
        for (String workType : split) {
            TbExamQuestionWorkType examQuestionWorkType = new TbExamQuestionWorkType();
            examQuestionWorkType.setExamQuestionId(entity.getId())
                    .setWorkerType(workType);
            examQuestionWorkTypeService.save(examQuestionWorkType);
        }
    }


}
