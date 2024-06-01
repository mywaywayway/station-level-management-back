package com.example.station_level_management_back.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.station_level_management_back.entity.MaterialwithdrawalrequestEntity;
import com.example.station_level_management_back.mapper.MaterialwithdrawalrequestMapper;
import com.example.station_level_management_back.service.MaterialwithdrawalrequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 材料出库申请表 服务实现类
 * </p>
 *
 * @author  My-way
 * @since 2024-05-05 21:24:43
 */
@Service
public class MaterialwithdrawalrequestServiceImpl extends ServiceImpl<MaterialwithdrawalrequestMapper, MaterialwithdrawalrequestEntity> implements MaterialwithdrawalrequestService {

    private  MaterialwithdrawalrequestMapper materialwithdrawalrequestMapper;



    @Autowired
    public  void  setMaterialwithdrawalrequestMapper(MaterialwithdrawalrequestMapper materialwithdrawalrequestMapper){
        this.materialwithdrawalrequestMapper=materialwithdrawalrequestMapper;
    }


    @Override

    public List<MaterialwithdrawalrequestEntity> getApplicationByName(String name ){
        return materialwithdrawalrequestMapper.selectList(Wrappers.<MaterialwithdrawalrequestEntity>lambdaQuery().eq(MaterialwithdrawalrequestEntity::getRequesterName,name));
    }

    @Override
    public  List<MaterialwithdrawalrequestEntity> getAllApplication(){
        return materialwithdrawalrequestMapper.selectList(null);
    }
}
