package com.example.station_level_management_back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@TableName("context_owner_user_view")
public class ContextOwnerUserViewEntity  {
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

    @TableField("user_name")
    private String userName;
}
