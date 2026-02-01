package com.safa.testspringboot.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="prestamo", schema = "public")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_usuario_prestador")
    private UsuarioPerfil prestador;

    @ManyToOne
    @JoinColumn(name="id_usuario_receptor")
    private UsuarioPerfil receptor;

    @ManyToOne
    @JoinColumn(name="id_ropa")
    private Ropa ropa;

    @Column(name="fecha_inicio")
    private LocalDateTime fechaInicio = LocalDateTime.now();

    @Column(name="fecha_final_acordada")
    private LocalDate fechaFinalAcordada;

    @Column(name="fecha_devolucion_real")
    private LocalDate fechaDevolucionReal;

    @Column(name="importe")
    private Double importe;

    @Column(name="estado")
    private String estado = "solicitado";
}
