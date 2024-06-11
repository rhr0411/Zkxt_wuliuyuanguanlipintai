package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbThirdUnit;

/**
 * <p>
 * 第三方单位 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
public interface ITbThirdUnitService extends IService<TbThirdUnit> {

    /**
     * 企业端-分页查询
     *
     * @param pageParam
     * @return
     */
    PageResult<TbThirdUnitEnterpriseDTO> getEnterprisePage(PageParam<TbThirdUnitEnterpriseDTO> pageParam);

    /**
     * 园区端-分页查询
     *
     * @param pageParam
     * @return
     */
    PageResult<TbThirdUnitParkDTO> getParkPage(PageParam<TbThirdUnitParkQueryDTO> pageParam);

    /**
     * 企业端-上传安全教育记录
     *
     * @param dto
     */
    void uploadSafeEduRecord(TbThirdUnitUploadSafeEduRecordDTO dto);

    /**
     * 企业端-安全教育记录查询
     *
     * @param dto
     * @return
     */
    TbThirdUnitSafeEduRecordDTO getSafeEduRecord(TbThirdUnitSafeEduRecordQueryDTO dto);

    /**
     * 园区端-审核
     *
     * @param dto
     */
    void audit(TbThirdUnitAuditDTO dto);

    /**
     * 注册
     *
     * @param dto
     */
    void register(TbThirdUnitRegisterDTO dto);
}
