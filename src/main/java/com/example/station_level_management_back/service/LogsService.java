package com.example.station_level_management_back.service;

import com.example.station_level_management_back.entity.LogsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
public interface LogsService extends IService<LogsEntity> {
    void deleteLogsByYearAndMonth(int year, int month);
}
