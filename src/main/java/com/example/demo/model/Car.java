package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "CARS")
@Entity
@NoArgsConstructor
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
    @Column(name = "LICENCE_PLATE", nullable = false, unique = true)
    private String licensePlate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    public Car(Long id, String make, String model, Integer productionYear, String licensePlate, Client client) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.productionYear = productionYear;
        this.licensePlate = licensePlate;
        this.client = client;
    }
}
