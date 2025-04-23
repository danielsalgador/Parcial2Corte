package com.example.PARCIAL2CORTE.Controller;

import com.example.PARCIAL2CORTE.Model.Equipo;
import com.example.PARCIAL2CORTE.Service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Equipo> equipos = equipoService.findAll();
        return ResponseEntity.ok().body(
                Map.of("mensaje", "Lista de equipos obtenida", "equipos", equipos)
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Equipo equipo) {
        try {
            Equipo guardado = equipoService.save(equipo);
            return ResponseEntity.status(201).body(
                    Map.of("mensaje", "Equipo guardado correctamente", "equipo", guardado)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("mensaje", "Error al guardar el equipo")
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        if (equipo != null) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Equipo encontrado", "equipo", equipo)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Equipo no encontrado")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Equipo equipoActualizado) {
        Equipo existente = equipoService.findById(id);
        if (existente != null) {
            existente.setNombre(equipoActualizado.getNombre());
            existente.setCiudad(equipoActualizado.getCiudad());

            Equipo actualizado = equipoService.save(existente);
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Equipo actualizado", "equipo", actualizado)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Equipo no encontrado para actualizar")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = equipoService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Equipo eliminado correctamente")
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Equipo no encontrado para eliminar")
            );
        }
    }
}
