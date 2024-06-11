package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 企业诚信等级编辑
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEnterpriseIntegrityLevelEditDTO", description = "企业诚信等级编辑")
public class TbEnterpriseIntegrityLevelEditDTO implements Serializable {

    @ApiModelProperty("主键ID")
    @NotEmpty(message = "主键ID不能为空")
    private String id;

    @ApiModelProperty("诚信等级：1-优 2-良 3-中等 4-差")
    @NotEmpty(message = "诚信等级不能为空")
    private String integrityLevel;

    @ApiModelProperty("诚信等级备注")
    private String integrityLevelRemark;

}
