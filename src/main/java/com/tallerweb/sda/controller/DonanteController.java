package com.tallerweb.sda.controller;

import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.service.DonanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donantes")
public class DonanteController {

    @Autowired
    private DonanteService donanteService;

    @GetMapping
    public List<Donante> obtenerTodos() {
        return donanteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donante> obtenerPorId(@PathVariable Long id) {
        Optional<Donante> donante = donanteService.obtenerPorId(id);
        return donante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Donante crear(@RequestBody Donante donante) {
        return donanteService.guardar(donante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donante> actualizar(@PathVariable Long id, @RequestBody Donante detallesDonante) {
        Optional<Donante> donanteExistente = donanteService.obtenerPorId(id);
        if (donanteExistente.isPresent()) {
            Donante donante = donanteExistente.get();
            donante.setNombre(detallesDonante.getNombre());
            donante.setEmail(detallesDonante.getEmail());
            donante.setTelefono(detallesDonante.getTelefono());
            return ResponseEntity.ok(donanteService.guardar(donante));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (donanteService.obtenerPorId(id).isPresent()) {
            donanteService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
