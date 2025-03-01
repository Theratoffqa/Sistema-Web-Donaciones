package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Solicitud;
import com.tallerweb.sda.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {
    @Autowired
    private SolicitudRepository solicitudRepository;

    public List<Solicitud> getAllSolicitudes() {
        return solicitudRepository.findAll();
    }

    public Optional<Solicitud> getSolicitudById(Long id) {
        return solicitudRepository.findById(id);
    }

    public Solicitud saveSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public void deleteSolicitud(Long id) {
        solicitudRepository.deleteById(id);
    }
}