package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Donacion;

import java.util.List;
import java.util.Optional;

public interface DonacionService {
    List<Donacion> getAllDonaciones();
    Optional<Donacion> getDonacionById(Long id);
    Donacion saveDonacion(Donacion donacion);
    void deleteDonacion(Long id);
}