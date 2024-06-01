package com.example.station_level_management_back.controller;

import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.LogsEntity;
import com.example.station_level_management_back.service.impl.LogsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@RestController
@RequestMapping("/logs-entity")
public class LogsController {

    private LogsServiceImpl logsService;

    @Autowired
    public  void  setLogsService(LogsServiceImpl logsService){
        this.logsService=logsService;
    }

    @PostMapping("/addLogs")
    public void addLogs(@RequestBody LogsEntity logsEntity){
        UUID uuid= UUID.randomUUID();
        logsEntity.setLogId(uuid.toString());
        LocalDateTime localDateTime=LocalDateTime.now();
        logsEntity.setCreatDate(localDateTime);
        logsService.save(logsEntity);
    }
    @GetMapping("/deleteLogsByYearAndMonth/{year}/{month}")
    public void deleteLogsByYearAndMonth(@PathVariable int year, @PathVariable int month){
        logsService.deleteLogsByYearAndMonth(year, month);
    }
}
