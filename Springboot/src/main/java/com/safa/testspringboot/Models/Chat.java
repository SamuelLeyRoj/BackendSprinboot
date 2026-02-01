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
@Table(name="chat" , schema = "public")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="tema")
    private String tema;

    @Column(name="creado_en")
    private LocalDateTime creadoEn = LocalDateTime.now();

    @OneToMany(mappedBy="chat", cascade=CascadeType.ALL)
    private List<ChatParticipantes> participantes;

    @OneToMany(mappedBy="chat", cascade=CascadeType.ALL)
    private List<Mensaje> mensajes;

}
