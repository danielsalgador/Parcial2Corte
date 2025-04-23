package com.example.PARCIAL2CORTE.Service;

import com.example.PARCIAL2CORTE.Model.Partido;
import com.example.PARCIAL2CORTE.Repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    // Método para obtener todos los partidos
    public List<Partido> findAll(){
        return partidoRepository.findAll();
    }

    // Método para guardar un partido con validación
    public Partido save(Partido partido) {
        validarPartido(partido); // Validación del partido antes de guardarlo
        return partidoRepository.save(partido);
    }

    // Método para encontrar un partido por ID
    public Partido findById(Long id) {
        return partidoRepository.findById(id).orElse(null);
    }

    // Método para actualizar un partido
    public Partido updateById(Long id, Partido partidoActualizado) {
        return partidoRepository.findById(id).map(partido -> {
            partido.setFecha(partidoActualizado.getFecha());
            partido.setEstadio(partidoActualizado.getEstadio());
            partido.setEquipoLocal(partidoActualizado.getEquipoLocal());
            partido.setEquipoVisita(partidoActualizado.getEquipoVisita());
            partido.setGolesLocal(partidoActualizado.getGolesLocal());
            partido.setGolesVisita(partidoActualizado.getGolesVisita());
            return partidoRepository.save(partido);
        }).orElseThrow(() -> new IllegalArgumentException("Partido no encontrado"));
    }

    // Método para eliminar un partido
    public boolean eliminar(Long id) {
        if (partidoRepository.existsById(id)) {
            Partido partido = partidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partido no encontrado"));

            // Si tienes la necesidad de eliminar las estadísticas relacionadas, puedes hacerlo aquí
            // por ejemplo: partido.getEstadisticaJugador().forEach(est -> estadisticaJugadorRepository.delete(est));

            partidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método para validar los datos del partido antes de guardarlo
    private void validarPartido(Partido partido) {
        if (partido.getEquipoLocal() == null || partido.getEquipoVisita() == null) {
            throw new IllegalArgumentException("Ambos equipos deben estar presentes");
        }

        if (partido.getFecha() == null) {
            throw new IllegalArgumentException("La fecha del partido no puede ser nula");
        }

        if (partido.getEstadio() == null || partido.getEstadio().isEmpty()) {
            throw new IllegalArgumentException("El estadio no puede ser nulo o vacío");
        }
    }
}
