package com.example.station_level_management_back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

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
@TableName("quota_ticket")
@ApiModel(value = "QuotaTicketEntity对象", description = "")
public class QuotaTicketEntity {

    @TableId(value = "quota_ticket_id", type = IdType.AUTO)
    private String quotaTicketId;

    @TableField("ticket_number")
    private String ticketNumber;

    @TableField("quota_ticket_amount")
    private BigDecimal quotaTicketAmount;

    @TableField("issued_status")
    private String issuedStatus;

    @TableField("quota_plate_number")
    private String quotaPlateNumber;

    @TableField("quota_invoice_code")
    private String quotaInvoiceCode;

    @TableField("ticket_id")
    private String ticketId;
}
