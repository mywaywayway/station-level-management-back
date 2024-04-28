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
 * @since 2024-04-17 21:32:21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ticket")
@ApiModel(value = "TicketEntity对象", description = "")
public class TicketEntity {

    @TableId(value = "ticket_id", type = IdType.AUTO)
    private String ticketId;

    @TableField("plate_number")
    private String plateNumber;

    @TableField("entrance_station")
    private String entranceStation;

    @TableField("exit_station")
    private String exitStation;

    @TableField("entrance_time")
    private LocalDateTime entranceTime;

    @TableField("exit_time")
    private LocalDateTime exitTime;

    @TableField("amount")
    private BigDecimal amount;

    @TableField("payment_status")
    private String paymentStatus;

    @TableField("pay_time")
    private LocalDateTime payTime;


}
