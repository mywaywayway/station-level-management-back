package com.example.station_level_management_back.mapper;

import com.example.station_level_management_back.entity.OutboundrecordsEntity;
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
public interface OutboundrecordsMapper extends BaseMapper<OutboundrecordsEntity> {

    @Select("SELECT MATERIALS.name as name, SUM(outboundrecords.quantity) as number FROM materials, outboundrecords WHERE materials.material_id = outboundrecords.item_id AND YEAR(outboundrecords.outbound_date) = #{year} AND MONTH(outboundrecords.outbound_date) = #{month} GROUP BY item_id")
    List<Map<Object, Object>> getMaterialsNumberByYearAndMonth(Integer year, Integer month);

}
