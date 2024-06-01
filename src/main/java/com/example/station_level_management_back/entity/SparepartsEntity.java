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
@TableName("spareparts")
@ApiModel(value = "SparepartsEntity对象", description = "")
public class SparepartsEntity {

    @TableId(value = "part_id", type = IdType.AUTO)
    private Integer partId;

    @TableField("`name`")
    private String name;

    @TableField("`description`")
    private String description;

    @TableField("quantity")
    private Integer quantity;

    @TableField("warehouse_id")
    private Integer warehouseId;


}
