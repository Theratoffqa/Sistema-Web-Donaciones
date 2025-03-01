package com.tallerweb.sda.controller;

import com.tallerweb.sda.model.Solicitud;
import com.tallerweb.sda.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;

    @GetMapping
    public List<Solicitud> getAllSolicitudes() {
        return solicitudService.getAllSolicitudes();
    }

    @GetMapping("/{id}")
    public Optional<Solicitud> getSolicitudById(@PathVariable Long id) {
        return solicitudService.getSolicitudById(id);
    }

    @PostMapping
    public Solicitud createSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudService.saveSolicitud(solicitud);
    }

    @DeleteMapping("/{id}")
    public void deleteSolicitud(@PathVariable Long id) {
        solicitudService.deleteSolicitud(id);
    }
}