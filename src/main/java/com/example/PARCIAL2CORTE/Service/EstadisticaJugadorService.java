package com.example.PARCIAL2CORTE.Service;

import com.example.PARCIAL2CORTE.Model.EstadisticaJugador;
import com.example.PARCIAL2CORTE.Repository.EstadisticaJugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadisticaJugadorService {

    @Autowired
    private EstadisticaJugadorRepository estadisticaJugadorRepository;

    public List<EstadisticaJugador>findAll(){
        return estadisticaJugadorRepository.findAll();
    }

    public EstadisticaJugador save(EstadisticaJugador estadistica){
        try {
            if (estadistica.getJugador() == null) {
                throw new IllegalArgumentException("Debe asociarse un jugador");
            }
            if (estadistica.getGoles() < 0) {
                throw new IllegalArgumentException("Los goles no pueden ser nulos o negativos");
            }
            if (estadistica.getAsistencias() < 0) {
                throw new IllegalArgumentException("Las asistencias no pueden ser nulas o negativas");
            }
            if (estadistica.getPartido() == null) {
                throw new IllegalArgumentException("El partido no puede ser nulo");
            }
            return estadisticaJugadorRepository.save(estadistica);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la estadística: " + e.getMessage());
        }
    }

    public EstadisticaJugador findById(Long id){
        return estadisticaJugadorRepository.findById(id).orElse(null);
    }

    public EstadisticaJugador updateById(Long id, EstadisticaJugador estadisticaActualizada){
        return estadisticaJugadorRepository.findById(id).map(estadistica ->{
            estadistica.setGoles(estadisticaActualizada.getGoles());
            estadistica.setAsistencias(estadisticaActualizada.getAsistencias());
            estadistica.setPartido(estadisticaActualizada.getPartido());
            estadistica.setJugador(estadisticaActualizada.getJugador());
            return estadisticaJugadorRepository.save(estadistica);
        }).orElse(null);
    }

    public boolean eliminar(Long id){
        if(estadisticaJugadorRepository.existsById(id)){
            estadisticaJugadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
