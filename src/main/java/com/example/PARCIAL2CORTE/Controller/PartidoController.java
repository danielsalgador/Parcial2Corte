package com.example.PARCIAL2CORTE.Controller;

import com.example.PARCIAL2CORTE.Model.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/partido")
public class PartidoController {
    @Autowired
    private PartidoService partidoService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Partido> partidos=partidoService.findAll();
        return ResponseEntity.ok().body(
                Map.of("mensaje","Lista de partidos obtenidos", "partidos", partidos)
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Partido partido){
        try {
            Partido guardado = partidoService.save(partido);
            return ResponseEntity.status(201).body(
                    Map.of("mensaje","Partido guardado correctamente", "partido", guardado)
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    Map.of("mensaje", e.getMessage())
            );
        }catch (Exception e){
            return ResponseEntity.status(500).body(
                    Map.of("mensaje", "Error al guardad el partido")
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Partido partido = partidoService.findById(id);
        if(partido !=null){
            return ResponseEntity.ok().body(
                    Map.of("mensaje","Partido encontrado","partido",partido)
            );
        }else{
            return ResponseEntity.status(404).body(
                    Map.of("mensaje","Partido no encontrado")
            );
        }
    }

}
