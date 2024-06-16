package com.example.station_level_management_back.service.impl;

import com.example.station_level_management_back.entity.TicketEntity;
import com.example.station_level_management_back.mapper.TicketMapper;
import com.example.station_level_management_back.service.TicketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
public class TicketServiceImpl extends ServiceImpl<TicketMapper, TicketEntity> implements TicketService {
    TicketMapper ticketMapper;

    @Autowired
    public void  setTicketMapper(TicketMapper ticketMapper){this.ticketMapper=ticketMapper;}

   @Override
    public List<TicketEntity> getAllTicket(){
        return ticketMapper.selectList(null);
   }
   /*
    *根据年份来获得每个月份的通行量
    *
    */
   @Override
    public List<Map<Object, Object>> getTicketNumberByYear(Integer Year){
       List<Map<Object,Object>> mapList=ticketMapper.getTicketNumberByYear(Year);
       List<Map<Object, Object>> resultList = new ArrayList<>();

       for (Map<Object, Object> map : mapList) {
           Map<Object, Object> newMap = new HashMap<>();
           for (Map.Entry<Object, Object> entry : map.entrySet()) {
               if (entry.getKey().equals("month")) {
                   newMap.put(entry.getKey(), entry.getValue()+"月");
               } else {
                   newMap.put(entry.getKey(), entry.getValue());
               }
           }
           resultList.add(newMap);
       }

       return resultList;
   }
   @Override

  public   List<Map<Object, Object>> getTicketMoneyByYear(int year){
       List<Map<Object,Object>> mapList=ticketMapper.getTicketMoneyByYear(year);
       List<Map<Object, Object>> resultList = new ArrayList<>();

       for (Map<Object, Object> map : mapList) {
           Map<Object, Object> newMap = new HashMap<>();
           for (Map.Entry<Object, Object> entry : map.entrySet()) {
               if (entry.getKey().equals("month")) {
                   newMap.put(entry.getKey(), entry.getValue()+"月");
               } else {
                   newMap.put(entry.getKey(), entry.getValue());
               }
           }
           resultList.add(newMap);
       }

       return resultList;
   }


}
