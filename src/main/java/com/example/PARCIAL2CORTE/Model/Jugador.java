package com.example.PARCIAL2CORTE.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnoreProperties("jugadores")
    private Equipo equipo;

    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
    @JsonManagedReference // Marca la relación como principal
    private List<EstadisticaJugador> estadisticas;

    public Integer getTotalGoles() {
        if (estadisticas == null || estadisticas.isEmpty()) {
            return 0;  // Si no hay estadísticas, el total de goles es 0
        }
        return estadisticas.stream()
                .mapToInt(EstadisticaJugador::getGoles)  // Asumiendo que 'getGoles()' devuelve el número de goles
                .sum();
    }

}
