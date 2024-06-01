package com.example.station_level_management_back.controller;

import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.MaterialsEntity;
import com.example.station_level_management_back.entity.MaterialwithdrawalrequestEntity;
import com.example.station_level_management_back.entity.OutboundrecordsEntity;
import com.example.station_level_management_back.mapper.OutboundrecordsMapper;
import com.example.station_level_management_back.service.impl.MaterialsServiceImpl;
import com.example.station_level_management_back.service.impl.MaterialwithdrawalrequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 材料出库申请表 前端控制器
 * </p>
 *
 * @author  My-way
 * @since 2024-05-05 21:24:43
 */
@RestController
@RequestMapping("/materialwithdrawalrequest-entity")
public class MaterialwithdrawalrequestController {

    private MaterialwithdrawalrequestServiceImpl materialwithdrawalrequestService;
    private MaterialsServiceImpl materialsService;

    private OutboundrecordsMapper outboundrecordsMapper;
    @Autowired
    public void setMaterialwithdrawalrequestService(MaterialwithdrawalrequestServiceImpl materialwithdrawalrequestService){
        this.materialwithdrawalrequestService=materialwithdrawalrequestService;
    }
    @Autowired
    public  void  setMaterialsService(MaterialsServiceImpl materialsService){
        this.materialsService=materialsService;
    }
    @Autowired
    public void setOutboundrecordsMapper(OutboundrecordsMapper outboundrecordsMapper){
        this.outboundrecordsMapper=outboundrecordsMapper;
    }

    @GetMapping("/getApplicationByName/{name}")
    public Result<?> getApplicationByName(@PathVariable String name){
        return Result.success(materialwithdrawalrequestService.getApplicationByName(name));
    }
    @PostMapping("/updateById")
    public Result<?> updateById(@RequestBody MaterialwithdrawalrequestEntity materialwithdrawalrequestEntity){
        if (materialwithdrawalrequestService.updateById(materialwithdrawalrequestEntity)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
    @GetMapping("/deleteById/{requestId}")
    public  Result<?> deleteById(@PathVariable String requestId){
        if (materialwithdrawalrequestService.removeById(requestId)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/addMaterialWithDrawalRequest")
    public Result<?> addMaterialWithDrawalRequest(@RequestBody MaterialwithdrawalrequestEntity materialwithdrawalrequestEntity){
        UUID uuid=UUID.randomUUID();
        materialwithdrawalrequestEntity.setRequestId(uuid.toString());
        materialwithdrawalrequestEntity.setStatus("未审核");
        if (materialwithdrawalrequestService.save(materialwithdrawalrequestEntity)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
    @PostMapping("/getAllApplication")
    public  Result<?> getAllApplication(){
        return Result.success(materialwithdrawalrequestService.getAllApplication());
    }

    /*
      审核通过，建立出库表，减少仓库数量
     */

    @PostMapping("/examine")
    public Result<?> examine(@RequestBody MaterialwithdrawalrequestEntity materialwithdrawalrequestEntity){
        MaterialsEntity materialsEntity=materialsService.selectMaterialsByName(materialwithdrawalrequestEntity.getItemName());
        if (materialsEntity.getQuantity()<materialwithdrawalrequestEntity.getQuantity()){
            return Result.fail("仓库数量不足");
        }else {
            materialwithdrawalrequestEntity.setStatus("审核通过");
            OutboundrecordsEntity outboundrecordsEntity=new OutboundrecordsEntity();
            UUID uuid=UUID.randomUUID();
            LocalDate localDateTime=LocalDate.now();
            outboundrecordsEntity.setRecordId(uuid.toString());
            outboundrecordsEntity.setOperator(materialwithdrawalrequestEntity.getRequesterName());
            outboundrecordsEntity.setDestination("前台");
            outboundrecordsEntity.setReason(materialwithdrawalrequestEntity.getReason());
            outboundrecordsEntity.setItemId(materialsEntity.getMaterialId());
            outboundrecordsEntity.setType(materialsService.judgementType(materialsEntity));
            outboundrecordsEntity.setQuantity(materialwithdrawalrequestEntity.getQuantity());
            outboundrecordsEntity.setOutboundDate(localDateTime);
            outboundrecordsMapper.insert(outboundrecordsEntity);
            materialsEntity.setQuantity(materialsEntity.getQuantity()-materialwithdrawalrequestEntity.getQuantity());
            materialsService.updateById(materialsEntity);
            materialwithdrawalrequestService.updateById(materialwithdrawalrequestEntity);
            return Result.success();
        }


    }
}
