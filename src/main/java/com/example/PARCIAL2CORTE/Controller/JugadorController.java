package com.example.PARCIAL2CORTE.Controller;

import com.example.PARCIAL2CORTE.Model.Jugador;
import com.example.PARCIAL2CORTE.Service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Jugador> jugadores = jugadorService.findAll();
        return ResponseEntity.ok().body(
                Map.of("mensaje", "Lista de jugadores obtenida", "jugadores", jugadores)
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Jugador jugador) {
        try {
            Jugador guardado = jugadorService.save(jugador);
            return ResponseEntity.status(201).body(
                    Map.of("mensaje", "Jugador guardado correctamente", "jugador", guardado)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("mensaje", "Error al guardar el jugador")
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Jugador jugador = jugadorService.findById(id);
        if (jugador != null) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Jugador encontrado", "jugador", jugador)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Jugador no encontrado")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Jugador jugadorActualizado) {
        Jugador existente = jugadorService.findById(id);
        if (existente != null) {
            existente.setNombre(jugadorActualizado.getNombre());
            existente.setPosicion(jugadorActualizado.getPosicion());

            Jugador actualizado = jugadorService.save(existente);
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Jugador actualizado", "jugador", actualizado)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Jugador no encontrado para actualizar")
            );
        }
    }

    @GetMapping("/gol/{id}")
    public ResponseEntity<?> getGolById(@PathVariable Long id) {
        if (id == 1) {
            // Crear un ejemplo de Gol
            Map<String, String> gol = Map.of("jugador", "Juan Pérez", "minuto", "45", "equipo", "Equipo A");
            return ResponseEntity.ok().body(Map.of("mensaje", "Gol encontrado", "gol", gol));
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Gol no encontrado"));
        }
    }

    @GetMapping("/totalgol/{id}")
    public ResponseEntity<?> getTotalGol(@PathVariable Long id) {
        // Lógica para obtener el total de goles del jugador
        Integer totalGoles = jugadorService.obtenerTotalGoles(id);

        if (totalGoles == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jugador no encontrado");
        }

        return ResponseEntity.ok(totalGoles);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = jugadorService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Jugador eliminado correctamente")
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Jugador no encontrado para eliminar")
            );
        }
    }
}



