package com.example.PARCIAL2CORTE.Repository;

import com.example.PARCIAL2CORTE.Model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query(value = "SELECT DISTINCT e.* FROM equipo e " +
            "JOIN partido p ON e.id_equipo = p.equipo_local OR e.id_equipo = p.equipo_visita " +
            "WHERE p.estadio = :estadio", nativeQuery = true)
    List<Equipo> findByEstadio(String estadio);
}
