package com.example.station_level_management_back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("logs")
@ApiModel(value = "LogsEntity对象", description = "")
public class LogsEntity {

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


}
