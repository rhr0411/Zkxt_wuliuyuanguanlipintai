package com.geovis.manager.system.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 王响
 * @since 2022-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemUser对象", description = "用户信息表")
@TableName("system_users")
public class SystemUser extends BaseEntity {

    @ApiModelProperty(value = "用户名")
    @Length(max = 64, message = "用户名最长不能超过{32}个字符")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String userPassword;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "真名")
    private String realName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "性别（1-男，0-女）")
    private String sex;

    @ApiModelProperty(value = "数据状态（1-未删除，0-删除）")
    private String dataStatus;

    @ApiModelProperty(value = "机构id")
    private String deptId;

    @ApiModelProperty(value = "最后一次修改密码时间")
    private LocalDateTime passwordLastChangeTime;

    @ApiModelProperty(value = "用户类型：1-企业用户 2-园区用户 3-第三方单位 4-游客")
    private String type;

    @ApiModelProperty(value = "职务")
    private String job;

    @ApiModelProperty(value = "入职时间")
    private LocalDate entryDate;

    @ApiModelProperty(value = "身份证号码")
    private String idNum;

    @ApiModelProperty(value = "在职状态：1-在职 0-离职")
    private String dutyStatus;

    @ApiModelProperty(value = "关联ID：企业ID、第三方单位ID")
    private String relatedId;

    @ApiModelProperty(value = "工种：1-特种作业 2-安全管理人员 3-危险化学品作业人员 4-剧毒化学品作业人员")
    private String workerType;

}
