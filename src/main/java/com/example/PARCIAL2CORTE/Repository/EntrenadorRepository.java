package com.example.PARCIAL2CORTE.Repository;

import com.example.PARCIAL2CORTE.Model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {

    @Query(value = "SELECT e.* FROM entrenador e " +
            "WHERE e.id_equipo = :idEquipo", nativeQuery = true)
    List<Entrenador> findByEquipoId(Long idEquipo);

    @Query(value = "SELECT e.* FROM entrenador e " +
            "WHERE e.especialidad = :especialidad", nativeQuery = true)
    List<Entrenador> findByEspecialidad(String especialidad);
}
