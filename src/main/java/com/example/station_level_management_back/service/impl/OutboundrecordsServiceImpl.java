package com.example.station_level_management_back.service.impl;

import com.example.station_level_management_back.entity.OutboundrecordsEntity;
import com.example.station_level_management_back.mapper.OutboundrecordsMapper;
import com.example.station_level_management_back.service.OutboundrecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@Service
public class OutboundrecordsServiceImpl extends ServiceImpl<OutboundrecordsMapper, OutboundrecordsEntity> implements OutboundrecordsService {

    @Autowired OutboundrecordsMapper outboundrecordsMapper;

    @Override
   public   List<Map<Object, Object>> getMaterialsNumberByYearAndMonth(Integer year, Integer month){
        return outboundrecordsMapper.getMaterialsNumberByYearAndMonth(year,month);
    }
}
