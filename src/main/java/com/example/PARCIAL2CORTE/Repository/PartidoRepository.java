package com.example.PARCIAL2CORTE.Repository;

import com.example.PARCIAL2CORTE.Model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    @Query(value = "SELECT * FROM partido p " +
            "JOIN equipo e_local ON p.equipo_local = e_local.id_equipo " +
            "JOIN equipo e_visita ON p.equipo_visita = e_visita.id_equipo " +
            "WHERE e_local.nombre = :nombreEquipo OR e_visita.nombre = :nombreEquipo",
            nativeQuery = true)
    List<Partido> findByEquipoNombre(String nombreEquipo);

    @Query(value = "SELECT p.id_partido, e_local.nombre AS equipo_local, e_visita.nombre AS equipo_visita, p.resultado " +
            "FROM partido p " +
            "JOIN equipo e_local ON p.equipo_local = e_local.id_equipo " +
            "JOIN equipo e_visita ON p.equipo_visita = e_visita.id_equipo",
            nativeQuery = true)
    List<Object[]> findResultadosDePartidos();
}
