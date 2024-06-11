package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 用户信息表保存DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemUserSaveDTO", description = "用户信息表保存DTO")
public class SystemUserSaveDTO implements Serializable {

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    @Length(max = 64, message = "用户名最长不能超过{64}个字符")
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "用户名不能为空")
    @Length(max = 255, message = "密码最长不能超过{255}个字符")
    private String userPassword;

    @ApiModelProperty(value = "昵称")
    @Length(max = 48, message = "昵称最长不能超过{48}个字符")
    private String nickName;

    @ApiModelProperty(value = "真名", required = true)
    @NotBlank(message = "真名不能为空")
    @Length(max = 24, message = "真名最长不能超过{24}个字符")
    private String realName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    @Length(max = 64, message = "邮箱最长不能超过{64}个字符")
    private String email;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    @Length(max = 11, message = "手机号最长不能超过{11}个字符")
    private String phone;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "性别（1-男，0-女）", required = true)
    @NotBlank(message = "性别（1-男，0-女）不能为空")
    private String sex;

    @ApiModelProperty(value = "机构id")
    private String deptId;

    @ApiModelProperty(value = "用户类型：1-企业用户 2-园区用户 3-第三方单位ID")
    @NotBlank(message = "用户类型：1-企业用户 2-园区用户 3-第三方单位ID 不能为空")
    private String type;

    @ApiModelProperty(value = "职务")
    private String job;

    @ApiModelProperty(value = "入职时间")
    private LocalDate entryDate;

    @ApiModelProperty(value = "身份证号码")
    private String idNum;

    @ApiModelProperty(value = "在职状态：1-在职 0-离职")
    @NotBlank(message = "在职状态：1-在职 0-离职 不能为空")
    private String dutyStatus;

    @ApiModelProperty(value = "关联ID：企业ID、第三方单位ID")
    @NotBlank(message = "关联ID：企业ID、第三方单位ID 不能为空")
    private String relatedId;

    @ApiModelProperty(value = "工种：1-特种作业 2-安全管理人员 3-危险化学品作业人员 4-剧毒化学品作业人员")
    @NotBlank(message = "工种 不能为空")
    private String workerType;
}
