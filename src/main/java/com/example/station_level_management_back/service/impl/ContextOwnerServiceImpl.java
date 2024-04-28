package com.example.station_level_management_back.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.station_level_management_back.entity.ContextOwnerEntity;
import com.example.station_level_management_back.mapper.ContextOwnerMapper;
import com.example.station_level_management_back.service.ContextOwnerService;
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
public class ContextOwnerServiceImpl extends ServiceImpl<ContextOwnerMapper, ContextOwnerEntity> implements ContextOwnerService {

    ContextOwnerMapper contextOwnerMapper;

    @Autowired

    public void  setContextOwnerMapper( ContextOwnerMapper contextOwnerMapper){
        this.contextOwnerMapper=contextOwnerMapper;
    }

    @Override
    public List<ContextOwnerEntity> getContextOwnerByUserId( String userId){
        return contextOwnerMapper.selectList(Wrappers.<ContextOwnerEntity>lambdaQuery().eq(ContextOwnerEntity::getUserId,userId));
    }



}
