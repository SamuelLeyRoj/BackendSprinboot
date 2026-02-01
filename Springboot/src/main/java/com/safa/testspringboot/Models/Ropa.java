package com.safa.testspringboot.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ropa",schema = "public")
public class Ropa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    private UsuarioPerfil usuario;

    @Column(name="nombre_prenda", nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name="estilo", nullable = false)
    private Estilo estilo;

    @Column(name="foto")
    private String foto;

    @Enumerated(EnumType.STRING)
    @Column(name="talla", nullable = false)
    private Talla talla;

    @Column(name="estado")
    private String estado = "disponible";





}
