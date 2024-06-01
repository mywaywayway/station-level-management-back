package com.example.station_level_management_back.service;

import com.example.station_level_management_back.entity.MaterialwithdrawalrequestEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 材料出库申请表 服务类
 * </p>
 *
 * @author  My-way
 * @since 2024-05-05 21:24:43
 */
public interface MaterialwithdrawalrequestService extends IService<MaterialwithdrawalrequestEntity> {

    List<MaterialwithdrawalrequestEntity> getApplicationByName(String name);

    List<MaterialwithdrawalrequestEntity> getAllApplication();
}
