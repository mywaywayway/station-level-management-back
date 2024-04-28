package com.example.station_level_management_back.controller;

import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.UserEntity;
import com.example.station_level_management_back.service.impl.UserServiceImpl;
import lombok.experimental.Accessors;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  My-way
 * @since 2024-04-17 21:32:21
 */
@Accessors
@RestController
@RequestMapping("/user-entity")
public class UserController {
   private UserServiceImpl userServiceImpl;

   @Autowired
   public void setUserServiceImpl(UserServiceImpl userServiceImpl){
       this.userServiceImpl=userServiceImpl;
   }
@PostMapping("/login")
 public Result<?> login(@RequestBody UserEntity userEntity){
//    return Result.success(userEntity);
    UserEntity userEntity1=userServiceImpl.getById(userEntity.getUserId());
    if (userEntity1 == null){
        return Result.fail("账号为空");
    }
    if (userEntity1.getApplicationRegistration()==0){
        return Result.fail("账号正在审核");
    }
    boolean flag= Objects.equals(userEntity1.getPassword(), userEntity.getPassword());
    boolean flag1= Objects.equals(userEntity.getUserType(), userEntity1.getUserType());
   if (flag&&flag1){
       return Result.success(userEntity1);
   } else {
       return Result.fail("密码或人员类型错误");
   }
 }

    @PostMapping("/addUser")
    public  Result<?> addUser(@RequestBody UserEntity userEntity){
        UserEntity userEntity1=userServiceImpl.getById(userEntity.getUserId());
        if (userEntity1!=null){
            return Result.fail("账号已存在");
        }else {
            userServiceImpl.saveOrUpdate(userEntity);
            return Result.success("注册申请成功，请耐心等待");
        }
    }

    @GetMapping("/getUserById/{userId}")
    public Result<?> getUserById(@PathVariable String userId){
       return Result.success(userServiceImpl.getById(userId));
    }

    @PostMapping("updateUserById")
   public Result<?> updateUserById(@RequestBody UserEntity userEntity){
      if (userServiceImpl.updateById(userEntity))
       return Result.success("修改成功");
      else {
          return Result.fail("修改失败");
      }
    }
}
