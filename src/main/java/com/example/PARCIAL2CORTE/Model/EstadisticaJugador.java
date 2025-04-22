package com.example.PARCIAL2CORTE.Model;

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
    private Partido partido;
    
    @ManyToOne
    @JoinColumn(name="idJugador")
    private Jugador jugador;

    private int minutosJugados;
    private int goles;
    private int asistencias;
    private int tarjetasAmarillas;
    private int tarjetasRojas;

}
