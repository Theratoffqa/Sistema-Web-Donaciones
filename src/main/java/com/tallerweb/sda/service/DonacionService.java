package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Donacion;
import com.tallerweb.sda.repository.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonacionService {
    @Autowired
    private DonacionRepository donacionRepository;

    public List<Donacion> getAllDonaciones() {
        return donacionRepository.findAll();
    }

    public Optional<Donacion> getDonacionById(Long id) {
        return donacionRepository.findById(id);
    }

    public Donacion saveDonacion(Donacion donacion) {
        return donacionRepository.save(donacion);
    }

    public void deleteDonacion(Long id) {
        donacionRepository.deleteById(id);
    }
}