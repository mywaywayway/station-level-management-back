package com.example.station_level_management_back.mapper;

import com.example.station_level_management_back.entity.TicketEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@Mapper
public interface TicketMapper extends BaseMapper<TicketEntity> {
    /*
      得到每月的通行量
     */
    @Select("SELECT MONTH(entrance_time) as month, COUNT(*) as count FROM ticket WHERE YEAR(entrance_time) = #{year} GROUP BY MONTH(entrance_time)")
    List<Map<Object, Object>> getTicketNumberByYear(int year);
    /*
      得到每月的收入
     */
    @Select("SELECT MONTH(entrance_time) as month, SUM(amount) as total_income FROM ticket WHERE YEAR(entrance_time) = #{year} GROUP BY MONTH(entrance_time)")
    List<Map<Object, Object>> getTicketMoneyByYear(int year);
}
