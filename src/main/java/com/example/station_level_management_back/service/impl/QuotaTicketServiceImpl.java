package com.example.station_level_management_back.service.impl;

import com.example.station_level_management_back.entity.QuotaTicketEntity;
import com.example.station_level_management_back.mapper.QuotaTicketMapper;
import com.example.station_level_management_back.service.QuotaTicketService;
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
public class QuotaTicketServiceImpl extends ServiceImpl<QuotaTicketMapper, QuotaTicketEntity> implements QuotaTicketService {

    private  QuotaTicketMapper quotaTicketMapper;

    @Autowired
    public void  setQuotaTicketMapper(QuotaTicketMapper quotaTicketMapper){
        this.quotaTicketMapper=quotaTicketMapper;
    }

    @Override
    public List<QuotaTicketEntity> getAllQuotaticket(){
        return quotaTicketMapper.selectList(null);
    }

}
