package com.example.PARCIAL2CORTE.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="entrenador")
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEntrenador;

    private String nombre;
    private String especialidad;

    @ManyToOne
    @JoinColumn(name="id_equipo")
    @JsonIgnoreProperties("entrenador")

    private Equipo equipo;
}
