package com.example.PARCIAL2CORTE.Controller;

import com.example.PARCIAL2CORTE.Model.Partido;
import com.example.PARCIAL2CORTE.Service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Partido> partidos = partidoService.findAll();
        return ResponseEntity.ok().body(
                Map.of("mensaje", "Lista de partidos obtenidos", "partidos", partidos)
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Partido partido) {
        try {
            Partido guardado = partidoService.save(partido);
            return ResponseEntity.status(201).body(
                    Map.of("mensaje", "Partido guardado correctamente", "partido", guardado)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("mensaje", e.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("mensaje", "Error al guardar el partido")
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Partido partido = partidoService.findById(id);
        if (partido != null) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Partido encontrado", "partido", partido)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Partido no encontrado")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Partido partidoActualizado) {
        Partido partidoExistente = partidoService.findById(id);
        if (partidoExistente != null) {
            partidoExistente.setFecha(partidoActualizado.getFecha());
            partidoExistente.setEstadio(partidoActualizado.getEstadio());

            Partido actualizado = partidoService.save(partidoExistente);
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Partido actualizado correctamente", "partido", actualizado)
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Partido no encontrado para actualizar")
            );
        }
    }

    @GetMapping("/resultado/{id}")
    public ResponseEntity<?> obtenerResultados() {
        List<Partido> partidosConResultados = partidoService.findAll(); // Obtener todos los partidos
        // Filtrar solo los partidos con resultados (usando el valor -1 como marcador de goles no asignados)
        List<Partido> partidosConResultadosFiltrados = partidosConResultados.stream()
                .filter(partido -> partido.getGolesLocal() != -1 && partido.getGolesVisita() != -1)
                .collect(Collectors.toList());

        if (partidosConResultadosFiltrados.isEmpty()) {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "No hay partidos con resultados registrados")
            );
        } else {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Resultados de los partidos obtenidos", "partidos", partidosConResultadosFiltrados)
            );
        }
    }

    @PutMapping("/resultado/{id}")
    public ResponseEntity<?> actualizarResultado(@PathVariable Long id, @RequestBody Map<String, Integer> resultados) {
        Partido partidoExistente = partidoService.findById(id);
        if (partidoExistente != null) {
            Integer golesLocal = resultados.get("golesLocal");
            Integer golesVisita = resultados.get("golesVisita");

            // Si los goles no son null, actualizamos
            if (golesLocal != null && golesVisita != null) {
                partidoExistente.setGolesLocal(golesLocal);
                partidoExistente.setGolesVisita(golesVisita);

                // Guardar partido con resultados actualizados
                Partido partidoActualizado = partidoService.save(partidoExistente);
                return ResponseEntity.ok().body(
                        Map.of("mensaje", "Resultado actualizado correctamente", "partido", partidoActualizado)
                );
            } else {
                return ResponseEntity.badRequest().body(
                        Map.of("mensaje", "Debe proporcionar los goles de ambos equipos")
                );
            }
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Partido no encontrado")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = partidoService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Partido eliminado correctamente")
            );
        } else {
            return ResponseEntity.status(404).body(
                    Map.of("mensaje", "Partido no encontrado para eliminar")
            );
        }
    }
}
