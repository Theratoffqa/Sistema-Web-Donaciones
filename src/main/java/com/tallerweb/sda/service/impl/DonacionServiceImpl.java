package com.tallerweb.sda.service.impl;

import com.tallerweb.sda.model.Donacion;
import com.tallerweb.sda.repository.DonacionRepository;
import com.tallerweb.sda.service.DonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonacionServiceImpl implements DonacionService {
    @Autowired
    private DonacionRepository donacionRepository;

    @Override
    public List<Donacion> getAllDonaciones() {
        return donacionRepository.findAll();
    }

    @Override
    public Optional<Donacion> getDonacionById(Long id) {
        return donacionRepository.findById(id);
    }

    @Override
    public Donacion saveDonacion(Donacion donacion) {
        return donacionRepository.save(donacion);
    }

    @Override
    public void deleteDonacion(Long id) {
        donacionRepository.deleteById(id);
    }
}