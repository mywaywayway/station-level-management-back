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
 * 材料出库申请表
 * </p>
 *
 * @author  My-way
 * @since 2024-05-05 21:24:43
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("materialwithdrawalrequest")
@ApiModel(value = "MaterialwithdrawalrequestEntity对象", description = "材料出库申请表")
public class MaterialwithdrawalrequestEntity {

    @ApiModelProperty("申请ID，采用UUID格式")
    @TableId(value = "request_id", type = IdType.AUTO)
    private String requestId;

    @ApiModelProperty("申请人姓名，不能为空")
    @TableField("requester_name")
    private String requesterName;

    @ApiModelProperty("申请日期，不能为空")
    @TableField("request_date")
    private LocalDate requestDate;

    @ApiModelProperty("物品名称")
    @TableField("item_name")
    private String itemName;

    @ApiModelProperty("申请数量，不能为空")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("申请理由，不能为空")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("申请状态")
    @TableField("`status`")
    private String status;


}
