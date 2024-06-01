package com.example.station_level_management_back.mapper;

import com.example.station_level_management_back.entity.LogsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@Mapper
public interface LogsMapper extends BaseMapper<LogsEntity> {
    void deleteLogsByYearAndMonth(int year, int month);
}
