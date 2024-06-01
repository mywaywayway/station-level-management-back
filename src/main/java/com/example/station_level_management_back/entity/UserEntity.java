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
@TableName("user")
@ApiModel(value = "UserEntity对象", description = "")
public class UserEntity {

    @TableId(value = "user_id", type = IdType.AUTO)
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("sexy")
    private String sexy;

    @TableField("birthday")
    private LocalDate birthday;

    @TableField("telephone")
    private String telephone;

    @TableField("`password`")
    private String password;

    @TableField("user_avatar")
    private String userAvatar;

    @TableField("work_id")
    private String workId;

    @TableField("email")
    private String email;

    @TableField("user_type")
    private Integer userType;

    @TableField("application_registration")
    private  Integer applicationRegistration;

}
