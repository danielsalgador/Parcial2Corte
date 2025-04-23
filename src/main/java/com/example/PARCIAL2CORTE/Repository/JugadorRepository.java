package com.example.PARCIAL2CORTE.Repository;

import com.example.PARCIAL2CORTE.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    @Query(value = "SELECT j.* FROM jugador j " +
            "JOIN equipo e ON j.id_equipo = e.id_equipo " +
            "WHERE e.id_equipo = :idEquipo", nativeQuery = true)
    List<Jugador> findByEquipoId(Long idEquipo);

    @Query(value = "SELECT j.* FROM jugador j " +
            "JOIN estadistica_jugador ej ON j.id_jugador = ej.id_jugador " +
            "WHERE ej.minutos_jugados > :minutos", nativeQuery = true)
    List<Jugador> findByMinutosJugadosGreaterThan(int minutos);
}
