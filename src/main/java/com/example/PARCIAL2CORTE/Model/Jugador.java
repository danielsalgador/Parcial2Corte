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
@Table(name="jugador")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJugador;

    private String nombre;
    private String posicion;
    private int dorsal;
    private LocalDate fechanacimiento;
    private String nacionalidad;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
    private List<EstadisticaJugador> estadisticas;
}
