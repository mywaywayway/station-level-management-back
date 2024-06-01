package com.example.station_level_management_back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
 * @since 2024-04-25 18:48:01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("toll_invoice")
@ApiModel(value = "TollInvoiceEntity对象", description = "")
public class TollInvoiceEntity {

    @TableId(value = "invoice_id", type = IdType.AUTO)
    private String invoiceId;

    @ApiModelProperty("发票代码")
    @TableField("invoice_code")
    private String invoiceCode;

    @ApiModelProperty("发票号码")
    @TableField("invoice_number")
    private String invoiceNumber;

    @ApiModelProperty("发票打印方")
    @TableField("printer")
    private String printer;

    @ApiModelProperty("出口")
    @TableField("exit_gate")
    private String exitGate;

    @ApiModelProperty("入口")
    @TableField("entry_gate")
    private String entryGate;

    @ApiModelProperty("时间")
    @TableField("`timestamp`")
    private LocalDateTime timestamp;

    @ApiModelProperty("车型")
    @TableField("vehicle_type")
    private String vehicleType;

    @ApiModelProperty("限重")
    @TableField("weight_limit")
    private BigDecimal weightLimit;

    @ApiModelProperty("车重")
    @TableField("vehicle_weight")
    private BigDecimal vehicleWeight;

    @ApiModelProperty("收费员")
    @TableField("toll_collector")
    private String tollCollector;

    @ApiModelProperty("金额")
    @TableField("amount")
    private BigDecimal amount;


    @TableField("ticket_id")
    private String ticketId;
}
