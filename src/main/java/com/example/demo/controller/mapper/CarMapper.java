package com.example.demo.controller.mapper;

import com.example.demo.model.Car;
import com.example.demo.controller.dto.CarDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface CarMapper {
    Car mapToCar(CarDTO carDTO);
    List<Car> mapToCarList(List<CarDTO> carDTO);
    void updateCarFromDTO(CarDTO carDTO, @MappingTarget Car car);
}
