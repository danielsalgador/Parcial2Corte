package com.example.PARCIAL2CORTE.Service;

import com.example.PARCIAL2CORTE.Model.Equipo;
import com.example.PARCIAL2CORTE.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public Equipo save(Equipo equipo) {
        if (equipo.getNombre() == null || equipo.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }
        if (equipo.getCiudad() == null || equipo.getCiudad().isBlank()) {
            throw new IllegalArgumentException("La ciudad del equipo no puede estar vacía");
        }
        if (equipo.getFundacion() == null || equipo.getFundacion().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fundación debe ser válida y no estar en el futuro");
        }
        return equipoRepository.save(equipo);
    }

    public Equipo findById(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    public Equipo updateById(Long id, Equipo equipoActualizado) {
        return equipoRepository.findById(id).map(equipo -> {
            equipo.setNombre(equipoActualizado.getNombre());
            equipo.setCiudad(equipoActualizado.getCiudad());
            equipo.setFundacion(equipoActualizado.getFundacion());
            return equipoRepository.save(equipo);
        }).orElse(null);
    }

    public boolean eliminar(Long id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
