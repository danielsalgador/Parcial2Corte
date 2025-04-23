package com.example.PARCIAL2CORTE.Controller;

import com.example.PARCIAL2CORTE.Model.Entrenador;
import com.example.PARCIAL2CORTE.Service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Entrenador> entrenadores = entrenadorService.findAll();
        return ResponseEntity.ok().body(
                Map.of("mensaje", "Lista de entrenadores obtenida", "entrenadores", entrenadores)
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Entrenador entrenador) {
        try {
            Entrenador guardado = entrenadorService.save(entrenador);
            return ResponseEntity.status(201).body(
                    Map.of("mensaje", "Entrenador guardado correctamente", "entrenador", guardado)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("mensaje", "Error al guardar el entrenador")
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Entrenador entrenador = entrenadorService.findById(id);
        if (entrenador != null) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Entrenador encontrado", "entrenador", entrenador)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Entrenador no encontrado")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Entrenador entrenadorActualizado) {
        Entrenador existente = entrenadorService.findById(id);
        if (existente != null) {
            existente.setNombre(entrenadorActualizado.getNombre());
            existente.setEspecialidad(entrenadorActualizado.getEspecialidad());

            Entrenador actualizado = entrenadorService.save(existente);
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Entrenador actualizado", "entrenador", actualizado)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Entrenador no encontrado para actualizar")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = entrenadorService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Entrenador eliminado correctamente")
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Entrenador no encontrado para eliminar")
            );
        }
    }
}
