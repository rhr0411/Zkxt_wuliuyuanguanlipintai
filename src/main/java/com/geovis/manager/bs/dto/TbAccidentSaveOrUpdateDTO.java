package com.geovis.manager.bs.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 事故事件管理保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbAccidentSaveOrUpdateDTO", description = "事故事件管理保存或更新")
public class TbAccidentSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("事故类型:1-泄漏 2-爆炸 3-火灾 4-其他类型")
    private String type;

    @ApiModelProperty("发生时间")
    private LocalDateTime happenTime;

    @ApiModelProperty("事故地点")
    private String address;

    @ApiModelProperty("损失金额")
    private BigDecimal lossAmount;

    @ApiModelProperty("受伤人数")
    private Integer injuryNum;

    @ApiModelProperty("死亡人数")
    private Integer deadNum;

    @ApiModelProperty("事故描述")
    private String description;

    @ApiModelProperty("应急响应与救援情况")
    private String rescueDesc;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("附件列表")
    private List<String> fileIdList;

    @ApiModelProperty("图片列表")
    private List<String> imagesIdList;


}
