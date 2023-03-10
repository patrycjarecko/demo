package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME", nullable = false)
    private ERole name;
}
