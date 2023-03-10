package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CarDTO{
    private Long id;
    private String make;
    private String model;
    private Integer productionYear;
    private String licensePlate;
    private Long clientId;

}
