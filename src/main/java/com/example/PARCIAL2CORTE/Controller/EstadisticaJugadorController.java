package com.example.PARCIAL2CORTE.Controller;

import com.example.PARCIAL2CORTE.Model.EstadisticaJugador;
import com.example.PARCIAL2CORTE.Service.EstadisticaJugadorService; // Asegúrate de importar tu servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estadistica")
public class EstadisticaJugadorController {

    @Autowired
    private EstadisticaJugadorService estadisticaJugadorService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<EstadisticaJugador> estadisticas = estadisticaJugadorService.findAll();
        return ResponseEntity.ok().body(
                Map.of("mensaje", "Lista de estadísticas obtenida", "estadisticas", estadisticas)
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody EstadisticaJugador estadistica) {
        try {
            EstadisticaJugador guardada = estadisticaJugadorService.save(estadistica);
            return ResponseEntity.status(201).body(
                    Map.of("mensaje", "Estadística guardada correctamente", "estadistica", guardada)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("mensaje", "Error al guardar la estadística", "error", e.getMessage())
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        EstadisticaJugador estadistica = estadisticaJugadorService.findById(id);
        if (estadistica != null) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Estadística encontrada", "estadistica", estadistica)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Estadística no encontrada")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EstadisticaJugador estadisticaActualizada) {
        EstadisticaJugador existente = estadisticaJugadorService.findById(id);
        if (existente != null) {
            existente.setGoles(estadisticaActualizada.getGoles());
            existente.setAsistencias(estadisticaActualizada.getAsistencias());
            existente.setMinutosJugados(estadisticaActualizada.getMinutosJugados());
            existente.setTarjetasAmarillas(estadisticaActualizada.getTarjetasAmarillas());
            existente.setTarjetasRojas(estadisticaActualizada.getTarjetasRojas());

            EstadisticaJugador actualizada = estadisticaJugadorService.save(existente);
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Estadística actualizada", "estadistica", actualizada)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Estadística no encontrada para actualizar")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = estadisticaJugadorService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Estadística eliminada correctamente")
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Estadística no encontrada para eliminar")
            );
        }
    }
}
