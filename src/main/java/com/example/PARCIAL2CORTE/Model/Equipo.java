package com.example.PARCIAL2CORTE.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEquipo;

    private String nombre;
    private String ciudad;
    private LocalDate fundacion;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("equipo")
    private List<Jugador> jugador;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("equipo")
    private List<Entrenador> entrenador;
}
