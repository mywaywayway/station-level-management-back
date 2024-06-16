package com.example.station_level_management_back.service;

import com.example.station_level_management_back.entity.OutboundrecordsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
public interface OutboundrecordsService extends IService<OutboundrecordsEntity> {

    List<Map<Object, Object>> getMaterialsNumberByYearAndMonth(Integer year, Integer month);
}
