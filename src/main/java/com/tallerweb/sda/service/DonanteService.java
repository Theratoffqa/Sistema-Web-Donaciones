package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.repository.DonanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DonanteService {

    @Autowired
    private DonanteRepository donanteRepository;

    public List<Donante> obtenerTodos() {
        return donanteRepository.findAll();
    }

    public Optional<Donante> obtenerPorId(Long id) {
        return donanteRepository.findById(id);
    }

    public Donante guardar(Donante donante) {
        return donanteRepository.save(donante);
    }

    public void eliminar(Long id) {
        donanteRepository.deleteById(id);
    }
}