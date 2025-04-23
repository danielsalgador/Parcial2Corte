package com.example.PARCIAL2CORTE.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name="partido")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPartido;

    private LocalDate fecha;
    private String estadio;

    @ManyToOne
    @JoinColumn(name="equipo_local")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name="equipo_visita")
    private Equipo equipoVisita;

    private int golesLocal;
    private int golesVisita;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"partido"})
    private List<EstadisticaJugador> estadisticaJugador;


}
