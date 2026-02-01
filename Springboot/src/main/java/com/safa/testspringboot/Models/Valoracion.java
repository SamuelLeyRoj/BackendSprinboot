package com.safa.testspringboot.Models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "valoracion",schema = "public")
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_intercambio", nullable = false)
    private Intercambio intercambio;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioPerfil usuario;

    @Column(name="puntuacion")
    private int puntuacion;

    @Column(name="comentario")
    private String comentario;


}
