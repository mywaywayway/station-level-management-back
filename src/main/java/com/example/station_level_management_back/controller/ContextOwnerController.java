package com.example.station_level_management_back.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.ContextOwnerEntity;
import com.example.station_level_management_back.service.impl.ContextOwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@RestController
@RequestMapping("/context-owner-entity")
public class ContextOwnerController {

    private ContextOwnerServiceImpl contextOwnerServiceImpl;

    @Autowired

    public void setContextOwnerServiceImpl (ContextOwnerServiceImpl contextOwnerServiceImpl){
        this.contextOwnerServiceImpl=contextOwnerServiceImpl;
    }

    @GetMapping("/getContextOwnerById/{userId}")
    public Result<?> getContextOwnerByI(@PathVariable String userId){
        List<ContextOwnerEntity> contextOwnerEntityList;
        contextOwnerEntityList= contextOwnerServiceImpl.getContextOwnerByUserId(userId);
        return Result.success(contextOwnerEntityList);
    }

    @PostMapping("/updateContextOwnerById")
    public Result<?> updateContextOwnerById(@RequestBody ContextOwnerEntity contextOwnerEntity){
        if (contextOwnerServiceImpl.updateById(contextOwnerEntity)){
            return Result.success();

        }else {
            return Result.fail();
        }
    }

    @PostMapping("/updateContextOwnerOffDutyTIme")
    public  Result<?> updateContextOwnerOffDutyTIme(@RequestBody ContextOwnerEntity contextOwnerEntity) {
        // 计算时间差

        Duration duration1 = Duration.between(contextOwnerEntity.getOffDutyTime(), contextOwnerEntity.getWorkingTime());

        Duration duration2 = Duration.between(contextOwnerEntity.getClockTime2(), contextOwnerEntity.getClockTime1());

        int comparisonResult = duration1.compareTo(duration2);
        if (comparisonResult < 0) {
            System.out.println("duration1 大于 duration2");
            return Result.fail("工时未满");
        } else {
            if (contextOwnerServiceImpl.updateById(contextOwnerEntity)){
                return Result.success();

            }else {
                return Result.fail("未知因素失败");
            }
        }
    }

}
