package com.safa.testspringboot.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ropa", schema = "public")
public class Ropa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = true) // true para probar sin usuario logueado
    private UsuarioPerfil usuario;

    @Column(name="nombre_prenda", nullable = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name="estilo", nullable = true) // true para probar
    private Estilo estilo;

    @Column(name="foto")
    private String foto; // Ruta de archivo opcional

    @Enumerated(EnumType.STRING)
    @Column(name="talla", nullable = true) // true para probar
    private Talla talla;

    @Column(columnDefinition = "TEXT") // Postgres TEXT para Base64 largo
    private String imagenBase64;

    @Column(name="estado")
    private String estado = "disponible";
}