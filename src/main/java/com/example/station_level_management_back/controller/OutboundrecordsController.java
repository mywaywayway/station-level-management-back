package com.example.station_level_management_back.controller;

import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.service.impl.OutboundrecordsServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@RestController
@RequestMapping("/outboundrecords-entity")
public class OutboundrecordsController {

    @Autowired
    OutboundrecordsServiceImpl outboundrecordsService;


    @GetMapping("/getMaterialsNumberByYearAndMonth/{year}/{month}")
    public Result<?> getMaterialsNumberByYearAndMonth(@PathVariable Integer year, @PathVariable Integer month){
       return Result.success(outboundrecordsService.getMaterialsNumberByYearAndMonth(year,month));
    }

}
