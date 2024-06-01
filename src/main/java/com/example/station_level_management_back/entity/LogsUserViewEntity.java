package com.example.station_level_management_back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("logs_user_view")
public class LogsUserViewEntity {
    @TableId(value = "log_id", type = IdType.AUTO)
    private String logId;

    @TableField("user_id")
    private String userId;

    @TableField("`type`")
    private String type;

    @TableField("operation")
    private String operation;

    @TableField("creat_date")
    private LocalDateTime creatDate;

    @TableField("user_name")
    private String userName;

}
