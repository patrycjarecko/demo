package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.controller.dto.CarDTO;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {
    private CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) {
        return ResponseEntity.ok().body(carService.getCar(carId));
    }

    @GetMapping("client/{clientId}")
    public ResponseEntity<List<Car>> getClientCars(@PathVariable(value = "clientId") Long clientId) {
        return ResponseEntity.ok().body(carService.getClientCars(clientId));
    }

    @PostMapping
    public Car createCar(@RequestBody CarDTO car) {
        return carService.createCar(car);
    }

    @PutMapping
    public Car updateCar(@RequestBody CarDTO car) {
        return carService.updateCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable(value = "id") Long carId) {
        carService.deleteCar(carId);
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
}
