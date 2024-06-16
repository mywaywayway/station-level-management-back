package com.example.station_level_management_back.controller;

import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.QuotaTicketEntity;
import com.example.station_level_management_back.entity.TicketEntity;
import com.example.station_level_management_back.entity.TollInvoiceEntity;
import com.example.station_level_management_back.service.impl.QuotaTicketServiceImpl;
import com.example.station_level_management_back.service.impl.TicketServiceImpl;
import com.example.station_level_management_back.service.impl.TollInvoiceServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
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
   private TollInvoiceServiceImpl tollInvoiceServiceImpl;
    private QuotaTicketServiceImpl quotaTicketServiceImpl;
    @Autowired

    public  void setTicketService(TicketServiceImpl ticketServiceImpl){this.ticketServiceImpl=ticketServiceImpl;}
  @Autowired
  public  void setTollInvoiceServiceImpl (TollInvoiceServiceImpl tollInvoiceServiceImpl){
        this.tollInvoiceServiceImpl=tollInvoiceServiceImpl;
  }
   @Autowired
    public  void  setQuotaTicketServiceImpl(QuotaTicketServiceImpl quotaTicketServiceImpl){
        this.quotaTicketServiceImpl=quotaTicketServiceImpl;
   }

   @GetMapping("/test")
   public void test(){

           for (int year = 2023; year <= 2024; year++) {
               for (int month = 1; month <= 12; month++) {
                   for (int i = 0; i < 2; i++) { // Insert 2 records per month
                       LocalDateTime entranceTime = LocalDateTime.of(year, month, 1, 8, 0);
                       LocalDateTime exitTime = entranceTime.plusHours(2);
                       LocalDateTime payTime = exitTime.plusMinutes(10);

                       TicketEntity ticket = new TicketEntity();
                       ticket.setTicketId(UUID.randomUUID().toString()); // Set the ticketId as a new UUID
                       ticket.setPlateNumber("Plate" + month + "-" + i);
                       ticket.setEntranceStation("Station" + month);
                       ticket.setExitStation("Station" + ((month % 12) + 1));
                       ticket.setEntranceTime(entranceTime);
                       ticket.setExitTime(exitTime);
                       ticket.setAmount(new BigDecimal("100.00"));
                       ticket.setPaymentStatus("已支付");
                       ticket.setPayTime(payTime);

                       ticketServiceImpl.save(ticket);
                   }
               }
           }
       }

    @GetMapping("/getTicketNumberByYear/{Year}")
    public Result<?> getTicketNumberByYear(@PathVariable Integer Year){
        return Result.success(ticketServiceImpl.getTicketNumberByYear(Year));
    }
    @GetMapping("/getTicketMoneyByYear/{year}")
    public Result<?> getTicketMoneyByYear(@PathVariable Integer year){
        return Result.success(ticketServiceImpl.getTicketMoneyByYear(year));
    }

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
        ticketServiceImpl.save(ticketEntity);
        if ((Objects.equals(ticketEntity.getAmount(), new BigDecimal(5)) || ticketEntity.getAmount().equals(new BigDecimal(10)) || ticketEntity.getAmount().equals(new BigDecimal(20)) || ticketEntity.getAmount().equals(new BigDecimal(50)))&&Objects.equals(ticketEntity.getPaymentStatus(), "已支付")){
            QuotaTicketEntity quotaTicketEntity=new QuotaTicketEntity();
            UUID uuid3 =UUID.randomUUID();
            UUID uuid1 =UUID.randomUUID();
            UUID uuid2 = UUID.randomUUID();
            quotaTicketEntity.setQuotaTicketId(uuid3.toString());
            quotaTicketEntity.setTicketId(ticketEntity.getTicketId());
            quotaTicketEntity.setQuotaTicketAmount(ticketEntity.getAmount());
            quotaTicketEntity.setTicketNumber(uuid1.toString());
            quotaTicketEntity.setQuotaInvoiceCode(uuid2.toString());
            quotaTicketEntity.setIssuedStatus(ticketEntity.getPaymentStatus());
            quotaTicketEntity.setQuotaPlateNumber(ticketEntity.getPlateNumber());
            quotaTicketServiceImpl.save(quotaTicketEntity);
        }

        else if (Objects.equals(ticketEntity.getPaymentStatus(), "已支付") ){
            TollInvoiceEntity tollInvoiceEntity = new TollInvoiceEntity();
            UUID uuid3 =UUID.randomUUID();
            UUID uuid1 =UUID.randomUUID();
            UUID uuid2 = UUID.randomUUID();
            tollInvoiceEntity.setAmount(ticketEntity.getAmount());
            tollInvoiceEntity.setTicketId(ticketEntity.getTicketId());
            tollInvoiceEntity.setEntryGate(ticketEntity.getEntranceStation());
            tollInvoiceEntity.setExitGate(ticketEntity.getExitStation());
            tollInvoiceEntity.setInvoiceId(uuid3.toString());
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
            tollInvoiceServiceImpl.save(tollInvoiceEntity);
        }

        return Result.success();
    }
    @PostMapping("/updateTicketById")
    public Result<?> updateTicketById(@RequestBody TicketEntity ticketEntity){
        if (ticketServiceImpl.updateById(ticketEntity)){
            if (Objects.equals(ticketEntity.getPaymentStatus(), "已支付")&& Objects.equals(ticketServiceImpl.getById(ticketEntity.getTicketId()).getPaymentStatus(), "未支付")
                    &&(Objects.equals(ticketEntity.getAmount(), new BigDecimal(5)) || ticketEntity.getAmount().equals(new BigDecimal(10)) || ticketEntity.getAmount().equals(new BigDecimal(20)) || ticketEntity.getAmount().equals(new BigDecimal(50)))
            ){
                QuotaTicketEntity quotaTicketEntity=new QuotaTicketEntity();
                UUID uuid =UUID.randomUUID();
                UUID uuid1 =UUID.randomUUID();
                UUID uuid2 = UUID.randomUUID();
                quotaTicketEntity.setQuotaTicketId(uuid.toString());
                quotaTicketEntity.setTicketId(ticketEntity.getTicketId());
                quotaTicketEntity.setQuotaTicketAmount(ticketEntity.getAmount());
                quotaTicketEntity.setTicketNumber(uuid1.toString());
                quotaTicketEntity.setQuotaInvoiceCode(uuid2.toString());
                quotaTicketEntity.setIssuedStatus(ticketEntity.getPaymentStatus());
                quotaTicketEntity.setQuotaPlateNumber(ticketEntity.getPlateNumber());
                quotaTicketServiceImpl.save(quotaTicketEntity);
            }

            else  if (Objects.equals(ticketServiceImpl.getById(ticketEntity.getTicketId()).getPaymentStatus(), "未支付")&&Objects.equals(ticketEntity.getPaymentStatus(), "已支付") ){
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
               tollInvoiceServiceImpl.save(tollInvoiceEntity);
            }

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
