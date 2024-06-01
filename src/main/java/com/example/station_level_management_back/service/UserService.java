package com.example.station_level_management_back.service;

import com.example.station_level_management_back.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
public interface UserService extends IService<UserEntity> {

    List<UserEntity> getAllEmployee();

    /*
     获得所有以及注册成功的员工和管理员
     */
    List<UserEntity> getAllEmployeeAndManagement();
    /*
     获得所有没完成注册的员工和管理员
     */
    List<UserEntity> getAllRegister();
}
