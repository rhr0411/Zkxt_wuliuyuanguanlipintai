package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 业务模块-安全与环境管理信息表
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_safe_enviro_manager")
@ApiModel(value = "TbSafeEnviroManager对象", description = "业务模块-安全与环境管理信息表")
public class TbSafeEnviroManager extends BaseEntity {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("所属类型")
    private String type;

    @ApiModelProperty("文件ID")
    private String fileId;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
