package com.tallerweb.sda.controller;

import com.tallerweb.sda.model.Donacion;
import com.tallerweb.sda.service.DonacionService;
import com.tallerweb.sda.service.DonanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donaciones")
public class DonacionController {
    private final DonacionService donacionService;
    private final DonanteService donanteService;

    @Autowired
    public DonacionController(DonacionService donacionService, DonanteService donanteService) {
        this.donacionService = donacionService;
        this.donanteService = donanteService;
    }

    @GetMapping
    public List<Donacion> getAllDonaciones() {
        return donacionService.getAllDonaciones();
    }

    @GetMapping("/{id}")
    public Optional<Donacion> getDonacionById(@PathVariable Long id) {
        return donacionService.getDonacionById(id);
    }

    @GetMapping("/donante/{donanteId}")
    public List<Donacion> getDonacionesByDonante(@PathVariable Long donanteId) {
        return donacionService.getDonacionesByDonante(donanteId);
    }

    @PostMapping("/donante/{donanteId}")
    public ResponseEntity<Donacion> createDonacion(@PathVariable Long donanteId, @RequestBody Donacion donacion) {
        return donanteService.getDonanteById(donanteId)
                .map(donante -> {
                    Donacion savedDonacion = donacionService.saveDonacion(donacion, donante);
                    return ResponseEntity.ok(savedDonacion);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donacion> updateDonacion(@PathVariable Long id, @RequestBody Donacion donacion) {
        Optional<Donacion> donacionExistente = donacionService.getDonacionById(id);
        if (donacionExistente.isPresent()) {
            donacion.setId(id);
            Donacion updatedDonacion = donacionService.saveDonacion(donacion, donacionExistente.get().getDonante());
            return ResponseEntity.ok(updatedDonacion);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteDonacion(@PathVariable Long id) {
        donacionService.deleteDonacion(id);
    }
}