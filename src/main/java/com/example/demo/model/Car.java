package com.example.demo.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "CARS")
@Entity
public class Car{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "MAKE", nullable = false)
    private String make;
    @Column(name = "MODEL", nullable = false)
    private String model;
    @Column(name = "PRODUCTION_YEAR", nullable = false)
    private Integer productionYear;
    @Column(name = "LICENCE_PLATE", nullable = false)
    private String licensePlate;

    @Column(name = "CLIENT_ID", nullable = false)
    private String clientId;

}
