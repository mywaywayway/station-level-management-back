package com.example.station_level_management_back.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.station_level_management_back.entity.MaterialsEntity;
import com.example.station_level_management_back.mapper.MaterialsMapper;
import com.example.station_level_management_back.service.MaterialsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@Service
public class MaterialsServiceImpl extends ServiceImpl<MaterialsMapper, MaterialsEntity> implements MaterialsService {

   private MaterialsMapper materialsMapper;

   @Autowired
   public void setMaterialsMapper(MaterialsMapper materialsMapper){
       this.materialsMapper=materialsMapper;
   }

   @Override
    public List<MaterialsEntity> getAllMaterialsName(){
       return materialsMapper.selectList(null);
   }

   @Override
    public MaterialsEntity selectMaterialsByName(String name){
      return materialsMapper.selectOne(Wrappers.<MaterialsEntity>lambdaQuery().eq(MaterialsEntity::getName,name));
   }

   @Override
    public String judgementType(MaterialsEntity materialsEntity){
        List<MaterialsEntity> materialsEntityList;
        materialsEntityList=materialsMapper.selectList(Wrappers.<MaterialsEntity>lambdaQuery().eq(MaterialsEntity::getName,materialsEntity.getName()).like(MaterialsEntity::getName,"发票"));
       List<MaterialsEntity> materialsEntityList1;
       materialsEntityList1=materialsMapper.selectList(Wrappers.<MaterialsEntity>lambdaQuery().eq(MaterialsEntity::getName,materialsEntity.getName()).like(MaterialsEntity::getName,"设备"));
       List<MaterialsEntity> materialsEntityList3;
       materialsEntityList3=materialsMapper.selectList(Wrappers.<MaterialsEntity>lambdaQuery().eq(MaterialsEntity::getName,materialsEntity.getName()).like(MaterialsEntity::getName,"传感器"));
       if (materialsEntityList.size()!=0){
           return "发票";
       } else if (materialsEntityList1.size()!=0) {
           return "设备";
       }else if (materialsEntityList3.size()!=0){
           return "传感器";
       }else {
           return "未知";
       }


   }
}
