package com.example.station_level_management_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.station_level_management_back")
public class StationLevelManagementBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationLevelManagementBackApplication.class, args);
	}

}
