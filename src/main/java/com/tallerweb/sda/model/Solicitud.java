package com.tallerweb.sda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Solo para cumplir con la restricción de Hibernate

    public Solicitud() {}
}
