package com.example.station_level_management_back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContextOwnerUserwObject {
    private String id;
    private String userId;
    private String date;
    private String workingTime;
    private String offDutyTime;
    private String clockTime1;
    private String clockTime2;
    private String userName;
}
