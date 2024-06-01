package com.example.station_level_management_back.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.station_level_management_back.entity.UserEntity;
import com.example.station_level_management_back.mapper.UserMapper;
import com.example.station_level_management_back.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public List<UserEntity> getAllEmployee(){
        return userMapper.selectList(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity
        ::getUserType,1).eq(UserEntity::getApplicationRegistration,1));
    }


    @Override
    public  List<UserEntity> getAllEmployeeAndManagement(){
        return userMapper.selectList(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getApplicationRegistration,1).and(i->i.eq(UserEntity::getUserType,1).or(j->j.eq(UserEntity::getUserType,2))));
    }

    @Override
    public  List<UserEntity> getAllRegister(){
        return userMapper.selectList(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getApplicationRegistration,0).and(i->i.eq(UserEntity::getUserType,1).or(j->j.eq(UserEntity::getUserType,2))));
    }
}
