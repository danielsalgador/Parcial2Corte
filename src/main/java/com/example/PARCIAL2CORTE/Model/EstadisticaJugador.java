package com.example.PARCIAL2CORTE.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="estadisticaJugador")
public class EstadisticaJugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEstadistica;
    @ManyToOne
    @JoinColumn(name="idPartido")
    @JsonBackReference // Marca la relación como secundaria
    private Partido partido;

    @ManyToOne
    @JoinColumn(name="idJugador")
    @JsonBackReference // Marca la relación como secundaria
    private Jugador jugador;


    private int minutosJugados;
    private Integer goles; ;
    private int asistencias;
    private int tarjetasAmarillas;
    private int tarjetasRojas;

}
