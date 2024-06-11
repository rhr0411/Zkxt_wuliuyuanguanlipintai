package com.geovis.manager.bs.service;

import com.geovis.manager.bs.entity.TbCommitment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 安全承诺书 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface ITbCommitmentService extends IService<TbCommitment> {

    /**
     * 监测年度履诺即将到期提醒
     */
    void checkYearPro();
}
