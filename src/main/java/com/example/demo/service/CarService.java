package com.example.demo.service;

import com.example.demo.controller.dto.CarDTO;
import com.example.demo.controller.mapper.CarMapper;
import com.example.demo.model.Car;
import com.example.demo.model.Client;
import com.example.demo.repository.CarRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;
    private ClientService clientService;

    private final CarMapper carMapper = Mappers.getMapper(CarMapper.class);
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public List<Car> getClientCars(Long clientId) {
        return carRepository.findByClientId(clientId);
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car with this id doesn't exist"));
    }

    public Car createCar(CarDTO car) {
        Client client = clientService.getClient(car.getClientId());
        Car car1 = carMapper.mapToCar(car);
        car1.setClient(client);
        return carRepository.save(car1);
    }

    @Transactional(rollbackFor = Exception.class)
    public Car updateCar(CarDTO car) {
        Car existingCar = carRepository.findById(car.getId())
                .orElseThrow(() -> new RuntimeException("Car with this id doesn't exist"));
        Client client = clientService.getClient(car.getClientId());

        existingCar.setLicensePlate(car.getLicensePlate());
        existingCar.setProductionYear(car.getProductionYear());
        existingCar.setModel(car.getModel());
        existingCar.setMake(car.getMake());
        existingCar.setClient(client);
        return carRepository.save(existingCar);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
