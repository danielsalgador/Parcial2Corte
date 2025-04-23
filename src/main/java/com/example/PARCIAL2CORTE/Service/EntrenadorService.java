package com.example.PARCIAL2CORTE.Service;

import com.example.PARCIAL2CORTE.Model.Entrenador;
import com.example.PARCIAL2CORTE.Repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public List<Entrenador> findAll() {
        return entrenadorRepository.findAll();
    }

    public Entrenador save(Entrenador entrenador) {
        // Validaciones
        if (entrenador.getNombre() == null || entrenador.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del entrenador no puede estar vacío");
        }

        if (entrenador.getEspecialidad() == null || entrenador.getEspecialidad().isBlank()) {
            throw new IllegalArgumentException("La especialidad del entrenador no puede estar vacía");
        }

        if (entrenador.getEquipo() == null) {
            throw new IllegalArgumentException("El equipo del entrenador no puede ser nulo");
        }

        return entrenadorRepository.save(entrenador);
    }

    public Entrenador findById(Long id) {
        return entrenadorRepository.findById(id).orElse(null);
    }

    public Entrenador updateById(Long id, Entrenador entrenadorActualizado) {
        return entrenadorRepository.findById(id).map(entrenador -> {
            // Validaciones
            if (entrenadorActualizado.getNombre() == null || entrenadorActualizado.getNombre().isBlank()) {
                throw new IllegalArgumentException("El nombre del entrenador no puede estar vacío");
            }

            if (entrenadorActualizado.getEspecialidad() == null || entrenadorActualizado.getEspecialidad().isBlank()) {
                throw new IllegalArgumentException("La especialidad del entrenador no puede estar vacía");
            }

            if (entrenadorActualizado.getEquipo() == null) {
                throw new IllegalArgumentException("El equipo del entrenador no puede ser nulo");
            }

            entrenador.setNombre(entrenadorActualizado.getNombre());
            entrenador.setEspecialidad(entrenadorActualizado.getEspecialidad());
            entrenador.setEquipo(entrenadorActualizado.getEquipo());
            return entrenadorRepository.save(entrenador);
        }).orElse(null);
    }

    public boolean eliminar(Long id) {
        if (entrenadorRepository.existsById(id)) {
            entrenadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
