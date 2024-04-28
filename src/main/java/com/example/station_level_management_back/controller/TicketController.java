package com.example.station_level_management_back.controller;

import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.TicketEntity;
import com.example.station_level_management_back.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@CrossOrigin
@RestController("TicketController")
@RequestMapping("/ticket-entity")
public class TicketController {

    private TicketServiceImpl ticketServiceImpl;


    @Autowired

    public  void setTicketService(TicketServiceImpl ticketServiceImpl){this.ticketServiceImpl=ticketServiceImpl;}


    @PostMapping("/getAllTicket")
    public Result<?> getAllTicket(){

        return  Result.success(ticketServiceImpl.getAllTicket());
    }

    @GetMapping("/getTicketById/{ticketId}")
    public  Result<?> getTicketById(@PathVariable String ticketId){
        if (ticketServiceImpl.getById(ticketId)!=null){
            return  Result.success(ticketServiceImpl.getById(ticketId));
        }else {
            return Result.fail("未找到对应通行记录");

        }
    }
    @PostMapping("/addTicket")
    public Result<?> addTicket(@RequestBody TicketEntity ticketEntity){
        UUID uuid=UUID.randomUUID();
        ticketEntity.setTicketId(uuid.toString());

        return Result.success(ticketServiceImpl.save(ticketEntity));
    }
    @PostMapping("/updateTicketById")
    public Result<?> updateTicketById(@RequestBody TicketEntity ticketEntity){
        if (ticketServiceImpl.updateById(ticketEntity)){
            return Result.success();
        }else {
            return  Result.fail();
        }
    }
    @GetMapping("/deleteTicketById/{ticketId}")
    public  Result<?> deleteTicketById(@PathVariable String ticketId){
        if (ticketServiceImpl.removeById(ticketId)){
            return Result.success();
        }else {
            return  Result.fail();
        }
    }
}
