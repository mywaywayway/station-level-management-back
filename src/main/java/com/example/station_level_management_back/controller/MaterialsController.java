package com.example.station_level_management_back.controller;

import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.MaterialsEntity;
import com.example.station_level_management_back.service.impl.MaterialsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@RestController
@RequestMapping("/materials-entity")
public class MaterialsController {


    private MaterialsServiceImpl materialsService;

    @Autowired
    public  void setMaterialsService(MaterialsServiceImpl materialsService){
        this.materialsService=materialsService;
    }

    @PostMapping("/getAllMaterials")
    public List<MaterialsEntity> getAllMaterials(){
        return materialsService.getAllMaterialsName();
    }

    @PostMapping("/updateById")
    public Result<?> updateById(@RequestBody MaterialsEntity materialsEntity){
        if (materialsService.updateById(materialsEntity)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
