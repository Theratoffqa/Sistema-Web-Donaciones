package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.repository.DonanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonanteService {
    @Autowired
    private DonanteRepository donanteRepository;

    public List<Donante> listarTodos() {
        return donanteRepository.findAll();
    }
}