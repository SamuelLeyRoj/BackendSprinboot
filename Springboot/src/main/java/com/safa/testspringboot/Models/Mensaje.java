package com.safa.testspringboot.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="mensaje", schema = "public")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_chat")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name="id_emisor")
    private UsuarioPerfil emisor;

    @Column(name="contenido")
    private String contenido;

    @Column(name="enviado_en")
    private LocalDateTime enviadoEn = LocalDateTime.now();

    @Column(name="leido")
    private Boolean leido = false;
}
