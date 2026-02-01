package com.safa.testspringboot.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="chat_participantes", schema = "public")
public class ChatParticipantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_chat")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private UsuarioPerfil usuario;

    @Column(name="rol")
    private String rol;
}
