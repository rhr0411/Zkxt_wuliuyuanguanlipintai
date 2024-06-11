package com.geovis.manager.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件-业务关联表
 * </p>
 *
 * @author 曾德实
 * @since 2020-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_file_business")
@ApiModel(value = "SystemFileBusiness对象", description = "附件-业务关联表")
public class SystemFileBusiness extends BaseEntity {

    @ApiModelProperty(value = "文件Id")
    private String fileId;

    @ApiModelProperty(value = "附加业务关联1")
    private String param1;

    @ApiModelProperty(value = "附加业务关联2")
    private String param2;

    @ApiModelProperty(value = "附加业务关联3")
    private String param3;

    @ApiModelProperty(value = "附加业务关联4")
    private String param4;

    @ApiModelProperty(value = "附加业务关联5")
    private String param5;

}
