package com.example.PARCIAL2CORTE.Repository;

import com.example.PARCIAL2CORTE.Model.EstadisticaJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadisticaJugadorRepository extends JpaRepository<EstadisticaJugador, Long> {

    @Query(value = "SELECT * FROM estadistica_jugador e " +
            "WHERE e.id_jugador = :idJugador", nativeQuery = true)
    List<EstadisticaJugador> findByJugadorId(Long idJugador);
}
