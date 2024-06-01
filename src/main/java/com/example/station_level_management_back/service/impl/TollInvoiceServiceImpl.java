package com.example.station_level_management_back.service.impl;

import com.example.station_level_management_back.entity.TollInvoiceEntity;
import com.example.station_level_management_back.mapper.TollInvoiceMapper;
import com.example.station_level_management_back.service.TollInvoiceService;
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
 * @since 2024-04-25 18:48:01
 */
@Service
public class TollInvoiceServiceImpl extends ServiceImpl<TollInvoiceMapper, TollInvoiceEntity> implements TollInvoiceService {

    private TollInvoiceMapper tollInvoiceMapper;

    @Autowired
    public  void setTollInvoiceMapper (TollInvoiceMapper tollInvoiceMapper){
        this.tollInvoiceMapper=tollInvoiceMapper;
    }

    @Override
    public List<TollInvoiceEntity> getAllTollInvoice(){
        return tollInvoiceMapper.selectList(null);
    }

}
