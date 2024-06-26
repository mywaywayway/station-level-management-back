package com.example.station_level_management_back.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.station_level_management_back.JwtUtil;
import com.example.station_level_management_back.PasswordUtils;
import com.example.station_level_management_back.Result;
import com.example.station_level_management_back.entity.OutboundrecordsEntity;
import com.example.station_level_management_back.entity.UserEntity;
import com.example.station_level_management_back.mapper.OutboundrecordsMapper;
import com.example.station_level_management_back.mapper.UserMapper;
import com.example.station_level_management_back.service.impl.OutboundrecordsServiceImpl;
import com.example.station_level_management_back.service.impl.UserServiceImpl;
import lombok.experimental.Accessors;
import org.apache.catalina.User;
import org.apache.catalina.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

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

   private UserMapper userMapper;
   @Autowired
    OutboundrecordsMapper outboundrecordsMapper;

   @Autowired
   public void setUserMapper(UserMapper userMapper){this.userMapper=userMapper;}

   @Autowired
   public void setUserServiceImpl(UserServiceImpl userServiceImpl){
       this.userServiceImpl=userServiceImpl;
   }
@PostMapping("/login")
 public Result<?> login(@RequestBody UserEntity userEntity){
    UserEntity userEntity1=userServiceImpl.getById(userEntity.getUserId());
    if (userEntity1 == null){
        return Result.fail("账号为空");
    }
    if (userEntity1.getApplicationRegistration()==0){
        return Result.fail("账号正在审核");
    }
    if (userEntity1.getApplicationRegistration()==2){
        return Result.fail("账号审核未通过");
    }
    boolean flag=PasswordUtils.checkPassword(userEntity.getPassword(),userEntity1.getPassword());

    boolean flag1=Objects.equals(userEntity.getUserType(), userEntity1.getUserType());
   if (flag&&flag1){
       return Result.success(JwtUtil.sign(userEntity.getUserId(),userEntity.getPassword()));
   } else {
       return Result.fail("密码或人员类型错误");
   }
 }
 @GetMapping("/changePassword/{oldPassword}/{newPassword}/{userId}")
 public Result<?> changePassword(@PathVariable String oldPassword,@PathVariable String newPassword,@PathVariable String userId){
       UserEntity userEntity=userServiceImpl.getById(userId);
       if (!PasswordUtils.checkPassword(oldPassword,userEntity.getPassword())){
           return Result.fail("密码错误");
       }else {
           String password=PasswordUtils.hashPassword(newPassword);
           userEntity.setPassword(password);
           userServiceImpl.updateById(userEntity);
           return Result.success("修改密码成功");
       }

 }
 @PostMapping("/test")
 public void  test(){
     UserEntity userEntity=userServiceImpl.getById("yanghuan");
     String password =PasswordUtils.hashPassword(userEntity.getPassword());
     userEntity.setPassword(password);
     userServiceImpl.updateById(userEntity);

 }

    @PostMapping("/addUser")
    public  Result<?> addUser(@RequestBody UserEntity userEntity){
        UserEntity userEntity1=userServiceImpl.getById(userEntity.getUserId());
        if (userEntity1!=null){
            return Result.fail("账号已存在");
        }else {
           String password= PasswordUtils.hashPassword(userEntity.getPassword());
            userEntity.setPassword(password);
            userEntity.setUserAvatar("https://ts4.cn.mm.bing.net/th?id=OIP-C.IykEwu6UUNOvq9LFU0d3kwAAAA&w=226&h=226&c=8&rs=1&qlt=90&o=6&dpr=2&pid=3.1&rm=2");
            userServiceImpl.saveOrUpdate(userEntity);
            return Result.success("注册申请成功，请耐心等待审核");
        }
    }

    @GetMapping("/getUserById/{userId}")
    public Result<?> getUserById(@PathVariable String userId){
       if (userServiceImpl.getById(userId)!=null){
           return Result.success(userServiceImpl.getById(userId));
       }else {
           return Result.fail("账号不存在");
       }
    }

    @GetMapping("/updatePasswordById/{id}/{oldPassword}/{newPassword}")
    public Result<?> updatePasswordById(@PathVariable String id,@PathVariable String oldPassword,@PathVariable String newPassword){
       UserEntity userEntity=userServiceImpl.getById(id);
      Boolean flag= PasswordUtils.checkPassword(oldPassword,userEntity.getPassword());
      if (flag){
          userEntity.setPassword(PasswordUtils.hashPassword(newPassword));
          userServiceImpl.updateById(userEntity);
          return Result.success("修改密码成功");
      }else {
          return Result.fail("旧密码错误");
      }
   }

    @PostMapping("updateUserById")
   public Result<?> updateUserById(@RequestBody UserEntity userEntity){
      if (userServiceImpl.updateById(userEntity))
       return Result.success("修改成功");
      else {
          return Result.fail("修改失败");
      }
    }

    @PostMapping("/getAllEmployeeAndManagement")
    public  Result<?> getAllEmployeeAndManagement(){
       return Result.success(userServiceImpl.getAllEmployeeAndManagement());
    }

    @GetMapping("/deleteUserInfoById/{id}")
    public Result<?> deleteUserInfo(@PathVariable String id){
       if (userServiceImpl.removeById(id)){
           return Result.success();
       }else {
           return  Result.fail("删除失败");
       }
    }
    @PostMapping("/getAllRegister")
    public Result<?> getAllRegister(){
     return Result.success(userServiceImpl.getAllRegister());
    }
}
