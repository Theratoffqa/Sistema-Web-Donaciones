package com.tallerweb.sda.controller;

import com.tallerweb.sda.model.Donacion;
import com.tallerweb.sda.service.DonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donaciones")
public class DonacionController {
    @Autowired
    private DonacionService donacionService;

    @GetMapping
    public List<Donacion> getAllDonaciones() {
        return donacionService.getAllDonaciones();
    }

    @GetMapping("/{id}")
    public Optional<Donacion> getDonacionById(@PathVariable Long id) {
        return donacionService.getDonacionById(id);
    }

    @PostMapping
    public Donacion createDonacion(@RequestBody Donacion donacion) {
        return donacionService.saveDonacion(donacion);
    }

    @DeleteMapping("/{id}")
    public void deleteDonacion(@PathVariable Long id) {
        donacionService.deleteDonacion(id);
    }
}