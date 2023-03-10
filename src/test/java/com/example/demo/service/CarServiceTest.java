package com.example.demo.service;

import com.example.demo.controller.dto.CarDTO;
import com.example.demo.model.Car;
import com.example.demo.model.Client;
import com.example.demo.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @Mock
    ClientService clientService;
    @InjectMocks
    CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveCar() {
        // given
        Client client = new Client(1L, "Jane", "Doe"); // should I move it
        CarDTO car = new CarDTO(1L, "Citroen", "C5", 2023, "BI 11111", client.getId());

        // when
        when(clientService.getClient(1L)).thenReturn(client);
        when(carRepository.save(any(Car.class))).then(returnsFirstArg()); //not sure

        // then
        Car savedCar = carService.createCar(car);
        assertEquals("C5", savedCar.getModel());
        verify(carRepository, times(1)).save(savedCar);
    }

    @Test
    void shouldGetAllCar() {
        // given
        Client client = new Client(1L, "Jane", "Doe"); // should I move it
        List<Car> list = new ArrayList<>();
        list.add(new Car(1L, "Citroen", "C5", 2023, "BI 11111", client));
        list.add(new Car(2L, "Citroen", "C4", 2020, "BI 17191", client));

        // when
        when(carRepository.findAll()).thenReturn(list);

        // then
        List<Car> testList = carService.getCars();
        assertEquals(2, testList.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void shouldGetCarById() {
        // given
        Client client = new Client(1L, "Jane", "Doe"); // should I move it
        Car car = new Car(1L, "Citroen", "C5", 2023, "BI 11111", client);

        // when
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        // then
        Car car2 = carService.getCar(1L);
        assertEquals("C5", car2.getModel());
        assertEquals(2023, car2.getProductionYear());
    }

}
