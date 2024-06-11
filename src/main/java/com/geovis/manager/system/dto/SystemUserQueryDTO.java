package com.geovis.manager.system.dto;

import cn.hutool.db.sql.Direction;
import com.geovis.common.mybatis.page.OrderBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 用户信息查询DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "SystemUserQueryDTO", description = "用户信息查询DTO")
public class SystemUserQueryDTO implements Serializable {

    @ApiModelProperty(value = "用户名")
    @Length(max = 64, message = "用户名最长不能超过{64}个字符")
    private String userName;

    @ApiModelProperty(value = "昵称")
    @Length(max = 48, message = "昵称最长不能超过{48}个字符")
    private String nickName;

    @ApiModelProperty(value = "真名")
    @Length(max = 24, message = "真名最长不能超过{24}个字符")
    private String realName;

    @ApiModelProperty(value = "手机号")
    @Length(max = 11, message = "手机号最长不能超过{11}个字符")
    private String phone;

    @ApiModelProperty(value = "性别（1-男，0-女）")
    private String sex;

    @ApiModelProperty(value = "机构id")
    private String deptId;

    @OrderBy(value = "a.create_time", orderType = Direction.DESC)
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "用户类型：1-企业用户 2-园区用户 3-第三方单位ID")
    private String type;

    @ApiModelProperty(value = "职务")
    private String job;

    @ApiModelProperty(value = "在职状态：1-在职 0-离职")
    private String dutyStatus;

    @ApiModelProperty(value = "关联ID：企业ID、第三方单位ID")
    private String relatedId;
}
