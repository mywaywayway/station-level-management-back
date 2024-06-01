package com.example.station_level_management_back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 材料入库申请表
 * </p>
 *
 * @author  My-way
 * @since 2024-05-05 21:24:43
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("materialinboundrequest")
@ApiModel(value = "MaterialinboundrequestEntity对象", description = "材料入库申请表")
public class MaterialinboundrequestEntity {

    @ApiModelProperty("申请ID，采用UUID格式")
    @TableId(value = "request_id", type = IdType.AUTO)
    private String requestId;

    @ApiModelProperty("申请人姓名，不能为空")
    @TableField("requester_name")
    private String requesterName;

    @ApiModelProperty("申请日期，不能为空")
    @TableField("request_date")
    private LocalDate requestDate;

    @ApiModelProperty("物品ID，关联到材料表或备件表的主键")
    @TableField("item_id")
    private String itemId;

    @ApiModelProperty("申请入库数量，不能为空")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("入库来源，不能为空")
    @TableField("`source`")
    private String source;

    @ApiModelProperty("申请入库理由，不能为空")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("申请状态")
    @TableField("`status`")
    private String status;


}
