package com.tallerweb.sda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "donaciones")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Solo para cumplir con la restricción de Hibernate

    public Donacion() {}
}
