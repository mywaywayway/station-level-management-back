package com.example.station_level_management_back.controller;

import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.ContextOwnerUserViewEntity;
import com.example.station_level_management_back.mapper.ContextOwnerUserViewMapper;
import com.example.station_level_management_back.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Context-owner-user-view-entity")
public class ContextOwnerUserViewController {
    private ContextOwnerUserViewMapper contextOwnerUserViewMapper;

    private UserServiceImpl userService;
    @Autowired
    public void setContextOwnerUserViewMapper(ContextOwnerUserViewMapper contextOwnerUserViewMapper){
        this.contextOwnerUserViewMapper=contextOwnerUserViewMapper;
    }
    @Autowired
    public  void  setUserService(UserServiceImpl userService){
        this.userService=userService;
    }
    @PostMapping("/getAllEmployee")
    public Result<?> getAllUser(){
        return Result.success(userService.getAllEmployee());
    }

    @PostMapping("/getALlContextOwnerUserView")
    public Result<?> getALlContextOwnerUserView(){

        return Result.success(contextOwnerUserViewMapper.selectList(null));
    }
}
