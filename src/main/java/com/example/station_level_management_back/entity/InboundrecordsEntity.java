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
 * 
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("inboundrecords")
@ApiModel(value = "InboundrecordsEntity对象", description = "")
public class InboundrecordsEntity {

    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    @TableField("item_id")
    private Integer itemId;

    @TableField("`type`")
    private String type;

    @TableField("quantity")
    private Integer quantity;

    @TableField("inbound_date")
    private LocalDate inboundDate;

    @TableField("`source`")
    private String source;

    @TableField("reason")
    private String reason;

    @TableField("operator")
    private String operator;


}
