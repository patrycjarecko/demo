package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {
    private CarService carService;

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getCar();
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    //    @GetMapping("/cars/{id}")
//    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId)
//            throws ResourceNotFoundException {
//        Car car = carRepository.findById(carId)
//                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + carId));
//        return ResponseEntity.ok().body(car);
//    }
//
//    @PostMapping("/cars")
//    public Car createCar(@Valid @RequestBody Car car) {
//        return carRepository.save(car);
//    }
//
//    @PutMapping("/cars/{id}")
//    public ResponseEntity<Car> updateCar(@PathVariable(value = "id") Long carId, @Valid @RequestBody Car carDetails) throws ResourceNotFoundException {
//        Car car = carRepository.findById(carId)
//                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + carId));
//
//        car.setMake(carDetails.getMake());
//        car.setModel(carDetails.getModel());
//        car.setProductionYear(carDetails.getProductionYear());
//        car.setLicensePlate(carDetails.getLicensePlate());
//
//        final Car updatedCar = carRepository.save(car);
//        return ResponseEntity.ok(updatedCar);
//    }
//
//    @DeleteMapping("/cars/{id}")
//    public Map<String, Boolean> deleteCar(@PathVariable(value = "id") Long carId)
//            throws ResourceNotFoundException {
//        Car car = carRepository.findById(carId)
//                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + carId));
//
//        carRepository.delete(car);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
}
