package com.safa.testspringboot.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "intercambio",schema = "public")
public class Intercambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario_ofertante")
    private UsuarioPerfil idUsuarioOfertante;

    @ManyToOne
    @JoinColumn(name = "id_usuario_solicitante")
    private UsuarioPerfil idUsuarioSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_ropa")
    private Ropa idRopa;

    @Column(nullable = false)
    private String estado = "solicitado";

    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    @Column(name = "fecha_acuerdo")
    private LocalDateTime fechaAcuerdo;
}
