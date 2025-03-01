package com.tallerweb.sda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping("/")
    public String mostrarPaginaPrincipal() {
        return "index"; // Retorna la plantilla index.html
    }
}