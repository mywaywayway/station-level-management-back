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
@TableName("warehouses")
@ApiModel(value = "WarehousesEntity对象", description = "")
public class WarehousesEntity {

    @TableId(value = "warehouse_id", type = IdType.AUTO)
    private Integer warehouseId;

    @TableField("`name`")
    private String name;

    @TableField("location")
    private String location;

    @TableField("capacity")
    private Integer capacity;


}
