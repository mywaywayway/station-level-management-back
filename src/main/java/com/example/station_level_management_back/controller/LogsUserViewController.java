package com.example.station_level_management_back.controller;


import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.mapper.LogsUserViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs-user-view-entity")
public class LogsUserViewController {

    private LogsUserViewMapper logsUserViewMapper;

    @Autowired
    public  void setLogsUserViewMapper( LogsUserViewMapper logsUserViewMapper)
    {
        this.logsUserViewMapper=logsUserViewMapper;
    }


    @PostMapping("/getAllLogsUserView")
    public Result<?> getAllLogsUserView(){
        return Result.success(logsUserViewMapper.selectList(null));
    }
}
