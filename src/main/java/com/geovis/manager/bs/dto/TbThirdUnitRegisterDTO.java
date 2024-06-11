package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 第三方单位注册
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitRegisterDTO", description = "第三方单位注册")
public class TbThirdUnitRegisterDTO implements Serializable {

    @ApiModelProperty("单位名称")
    @NotEmpty(message = "单位名称不能为空")
    private String name;

    @ApiModelProperty("主要负责人名称")
    @NotEmpty(message = "主要负责人名称不能为空")
    private String masterName;

    @ApiModelProperty("主要负责人电话")
    @NotEmpty(message = "主要负责人电话不能为空")
    private String masterPhone;

    @ApiModelProperty("资质等级")
    @NotEmpty(message = "资质等级不能为空")
    private String qualificationLevel;

    @ApiModelProperty("邮箱")
    @NotEmpty(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("单位地址")
    @NotEmpty(message = "单位地址不能为空")
    private String address;

    @ApiModelProperty("资质有效期")
    @NotNull(message = "资质有效期不能为空")
    private LocalDate expireDate;

    @ApiModelProperty("单位类别")
    @NotEmpty(message = "单位类别不能为空")
    private String type;

    @ApiModelProperty("关联企业ID列表")
    @NotNull(message = "关联企业ID列表不能为空")
    private List<String> enterpriseIdList;

    @ApiModelProperty("资质附件ID列表")
    private List<String> fileIdList;
}
