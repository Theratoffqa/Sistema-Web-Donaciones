package com.tallerweb.sda.controller;

import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.service.DonanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donantes")
public class DonanteController {
    @Autowired
    private DonanteService donanteService;

    @GetMapping
    public List<Donante> obtenerDonantes() {
        return donanteService.listarTodos();
    }
}