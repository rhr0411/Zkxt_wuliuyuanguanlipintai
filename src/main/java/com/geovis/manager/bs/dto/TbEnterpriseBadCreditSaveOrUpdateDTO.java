package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 企业不良信用管理保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEnterpriseBadCreditSaveOrUpdateDTO", description = "企业不良信用管理保存或更新")
public class TbEnterpriseBadCreditSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

    @ApiModelProperty("不良信用描述")
    @NotEmpty(message = "不良信用描述不能为空")
    private String blackDesc;

    @ApiModelProperty("附件ID列表")
    private List<String> fileIdList;

}
