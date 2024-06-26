package com.example.station_level_management_back.service.impl;

import com.example.station_level_management_back.entity.LogsEntity;
import com.example.station_level_management_back.mapper.LogsMapper;
import com.example.station_level_management_back.service.LogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, LogsEntity> implements LogsService {
    @Autowired
    private LogsMapper logsMapper;

    @Override
    public void deleteLogsByYearAndMonth(int year, int month){
        logsMapper.deleteLogsByYearAndMonth(year, month);
    }
}
