package com.example.PARCIAL2CORTE.Service;

import com.example.PARCIAL2CORTE.Model.Jugador;
import com.example.PARCIAL2CORTE.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    public Jugador save(Jugador jugador) {
        if (jugador.getNombre() == null || jugador.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío");
        }

        if (jugador.getPosicion() == null || jugador.getPosicion().isBlank()) {
            throw new IllegalArgumentException("La posición del jugador no puede estar vacía");
        }

        if (jugador.getDorsal() <= 0) {
            throw new IllegalArgumentException("El dorsal debe ser un número positivo");
        }

        if (jugador.getFechanacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }

        if (jugador.getNacionalidad() == null || jugador.getNacionalidad().isBlank()) {
            throw new IllegalArgumentException("La nacionalidad no puede estar vacía");
        }

        return jugadorRepository.save(jugador);
    }

    public Jugador findById(Long id) {
        return jugadorRepository.findById(id).orElse(null);
    }

    public Jugador updateById(Long id, Jugador jugadorActualizado) {
        return jugadorRepository.findById(id).map(jugador -> {
            jugador.setNombre(jugadorActualizado.getNombre());
            jugador.setPosicion(jugadorActualizado.getPosicion());
            jugador.setDorsal(jugadorActualizado.getDorsal());
            jugador.setFechanacimiento(jugadorActualizado.getFechanacimiento());
            jugador.setNacionalidad(jugadorActualizado.getNacionalidad());
            return jugadorRepository.save(jugador);
        }).orElse(null);
    }

    public boolean eliminar(Long id) {
        if (jugadorRepository.existsById(id)) {
            jugadorRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Integer obtenerTotalGoles(Long id) {
        Optional<Jugador> jugadorOpt = jugadorRepository.findById(id);
        return jugadorOpt.map(Jugador::getTotalGoles).orElse(null);
    }
}
