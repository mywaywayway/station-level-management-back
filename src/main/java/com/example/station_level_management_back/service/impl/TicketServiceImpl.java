package com.example.station_level_management_back.service.impl;

import com.example.station_level_management_back.entity.TicketEntity;
import com.example.station_level_management_back.mapper.TicketMapper;
import com.example.station_level_management_back.service.TicketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
