package com.example.station_level_management_back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
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
@TableName("context_owner")
@ApiModel(value = "ContextOwnerEntity对象", description = "")
public class ContextOwnerEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField("user_id")
    private String userId;

    @TableField("`date`")
    private LocalDate date;

    @TableField("working_time")
    private LocalDateTime workingTime;

    @TableField("off_duty_time")
    private LocalDateTime offDutyTime;

    @TableField("clock_time1")
    private LocalDateTime clockTime1;

    @TableField("clock_time2")
    private LocalDateTime clockTime2;


}
