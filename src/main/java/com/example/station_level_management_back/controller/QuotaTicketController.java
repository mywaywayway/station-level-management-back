package com.example.station_level_management_back.controller;

import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.QuotaTicketEntity;
import com.example.station_level_management_back.entity.TicketEntity;
import com.example.station_level_management_back.service.impl.QuotaTicketServiceImpl;
import com.example.station_level_management_back.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
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
@RequestMapping("/quota-ticket-entity")
public class QuotaTicketController {

  private QuotaTicketServiceImpl quotaTicketServiceImpl;
  private TicketServiceImpl ticketServiceImpl;
  @Autowired
    public  void setQuotaTicketServiceImpl(QuotaTicketServiceImpl quotaTicketServiceImpl){
      this.quotaTicketServiceImpl=quotaTicketServiceImpl;
  }
@Autowired
 public void setTicketServiceImpl(TicketServiceImpl ticketServiceImpl){
    this.ticketServiceImpl=ticketServiceImpl;
}

  @PostMapping("/getALlQuotaTicket")
  public Result<?> getAllQuotaTicket(){
    return Result.success(quotaTicketServiceImpl.getAllQuotaticket());
  }



  @GetMapping("/addQuotaTicketByTicketId/{ticketId}")
  public Result<?> addQuotaTicketByTicketId(@PathVariable String ticketId){
    TicketEntity ticketEntity=ticketServiceImpl.getById(ticketId);
    if (ticketEntity==null){
      return Result.fail("通行ID不存在");
    }
    if (Objects.equals(ticketEntity.getPaymentStatus(), "未支付")){
      return Result.fail("通行未支付");
    }
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
    return Result.success();
  }

}

