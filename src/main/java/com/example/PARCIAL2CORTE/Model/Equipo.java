package com.example.PARCIAL2CORTE.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEquipo;

    private String nombre;
    private String ciudad;
    private LocalDate fundacion;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Jugador> jugador;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Entrenador> entrenador;


}
