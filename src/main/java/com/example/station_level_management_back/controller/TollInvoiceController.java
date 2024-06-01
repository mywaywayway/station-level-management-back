package com.example.station_level_management_back.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.TicketEntity;
import com.example.station_level_management_back.entity.TollInvoiceEntity;
import com.example.station_level_management_back.mapper.TollInvoiceMapper;
import com.example.station_level_management_back.service.impl.TicketServiceImpl;
import com.example.station_level_management_back.service.impl.TollInvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  My-way
 * @since 2024-04-25 18:48:01
 */
@RestController
@RequestMapping("/toll-invoice-entity")
public class TollInvoiceController {
    private TollInvoiceServiceImpl tollInvoiceServiceImpl;
    private TicketServiceImpl ticketServiceImpl;

    private TollInvoiceMapper tollInvoiceMapper;
    @Autowired
     public  void setTollInvoiceMapper(TollInvoiceMapper tollInvoiceMapper){
        this.tollInvoiceMapper=tollInvoiceMapper;
    }
    @Autowired

    public  void  setTollInvoiceService(TollInvoiceServiceImpl tollInvoiceServiceImpl)
    {
        this.tollInvoiceServiceImpl=tollInvoiceServiceImpl;
    }
    @Autowired
    public  void  setTicketServiceImpl(TicketServiceImpl ticketServiceImpl){
        this.ticketServiceImpl=ticketServiceImpl;
    }

    @GetMapping("/addTollInvoice/{ticketId}")
    public Result<?> addTollInvoice(@PathVariable String ticketId){
        TicketEntity ticketEntity=new TicketEntity();
        ticketEntity=ticketServiceImpl.getById(ticketId);
        if (tollInvoiceMapper.selectOne(Wrappers.<TollInvoiceEntity>lambdaQuery().eq(TollInvoiceEntity::getTicketId,ticketId))!=null){
            return Result.fail("支付记录已存在");
        }
        if (ticketEntity==null){
            return Result.fail("通行记录ID不正确");
        }
        if (Objects.equals(ticketEntity.getPaymentStatus(), "未支付")){
            return Result.fail("未支付");
        }
        else {

            TollInvoiceEntity tollInvoiceEntity = new TollInvoiceEntity();
            UUID uuid =UUID.randomUUID();
            UUID uuid1 =UUID.randomUUID();
            UUID uuid2 = UUID.randomUUID();
            tollInvoiceEntity.setAmount(ticketEntity.getAmount());
            tollInvoiceEntity.setTicketId(ticketEntity.getTicketId());
            tollInvoiceEntity.setEntryGate(ticketEntity.getEntranceStation());
            tollInvoiceEntity.setExitGate(ticketEntity.getExitStation());
            tollInvoiceEntity.setInvoiceId(uuid.toString());
            tollInvoiceEntity.setInvoiceCode(uuid1.toString());
            tollInvoiceEntity.setInvoiceNumber(uuid2.toString());
            tollInvoiceEntity.setPrinter("xx收费站");
            tollInvoiceEntity.setWeightLimit(new BigDecimal(10));
            Random random= new Random();
            int num=random.nextInt(5)+1;
            if (num%2==1){
                tollInvoiceEntity.setVehicleType("轿车");
            }else {
                tollInvoiceEntity.setVehicleType("货车");
            }
            if (Objects.equals(tollInvoiceEntity.getVehicleType(), "轿车"))
                tollInvoiceEntity.setVehicleWeight(new BigDecimal(2));
            else {
                tollInvoiceEntity.setVehicleWeight(new BigDecimal(5));
            }
           return Result.success(tollInvoiceServiceImpl.save(tollInvoiceEntity));

        }

    }
    @PostMapping("/getAllTollInvoice")
    public Result<?> getAllTollInvoice(){
        return Result.success(tollInvoiceServiceImpl.getAllTollInvoice());
    }

    @PostMapping("/updateTollInvoiceById")
    public Result<?> updateTollInvoiceById(@RequestBody TollInvoiceEntity tollInvoiceEntity){
        if (tollInvoiceServiceImpl.updateById(tollInvoiceEntity)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
    @GetMapping("/deleteTollInvoiceById/{invoiceId}")
    public Result<?> deleteTollInvoiceById(@PathVariable String invoiceId){

        if (tollInvoiceServiceImpl.removeById(invoiceId))
        {
            return  Result.success();
        }
        else {
            return Result.fail();
        }

    }

    @PostMapping("/printTollInvoiceById")
    public Result<?> printTollInvoiceById(@RequestBody TollInvoiceEntity tollInvoiceEntity){

        try {
            PrintStream printStream= new PrintStream("D:\\1.txt");
            System.setOut(printStream);
            System.out.println("发票代码："+tollInvoiceEntity.getInvoiceCode());
            System.out.println("发票ID："+tollInvoiceEntity.getInvoiceNumber());
            System.out.println("入站口："+tollInvoiceEntity.getEntryGate());
            System.out.println("出站口："+tollInvoiceEntity.getExitGate());
            System.out.println("时间："+tollInvoiceEntity.getTimestamp());
            System.out.println("金额："+tollInvoiceEntity.getAmount());
            System.out.println("工作人员："+tollInvoiceEntity.getTollCollector());
            System.out.println("车辆类型："+tollInvoiceEntity.getVehicleType());
            System.out.println("车辆限重："+tollInvoiceEntity.getWeightLimit());
            System.out.println("车辆重量："+tollInvoiceEntity.getVehicleWeight());
            return Result.success();
        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);

        }

    }

}
