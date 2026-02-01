package com.safa.testspringboot.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuario_sesion" , schema = "public")
public class UsuarioSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="nombre_usuario", unique = true, nullable = false)
    private String nombre;

    @Column(name="contrasenia", nullable = false)
    private String contrasenia;

    @Column(name="fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @OneToOne(mappedBy="usuarioSesion", cascade=CascadeType.ALL)
    private UsuarioPerfil perfil;
}
