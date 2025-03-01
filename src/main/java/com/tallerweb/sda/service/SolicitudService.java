package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Solicitud;
import java.util.List;
import java.util.Optional;

public interface SolicitudService {
    List<Solicitud> getAllSolicitudes();
    Optional<Solicitud> getSolicitudById(Long id);
    Solicitud saveSolicitud(Solicitud solicitud);
    void deleteSolicitud(Long id);
}