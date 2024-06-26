package com.example.station_level_management_back.service;

import com.example.station_level_management_back.entity.MaterialsEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
public interface MaterialsService extends IService<MaterialsEntity> {

    List<MaterialsEntity> getAllMaterialsName();

    MaterialsEntity selectMaterialsByName(String name);

    String  judgementType( MaterialsEntity materialsEntity);
}
