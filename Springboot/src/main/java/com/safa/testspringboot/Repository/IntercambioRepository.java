package com.safa.testspringboot.Repository;

import com.safa.testspringboot.Dto.RopaTopDto;
import com.safa.testspringboot.Dto.UsuarioMasAceptadoDto;
import com.safa.testspringboot.Models.Intercambio;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntercambioRepository extends JpaRepository<Intercambio, Integer> {

    @Query("""
        SELECT new com.safa.testspringboot.Dto.RopaTopDto(
            r.id,
            r.nombre,
            r.talla,
            r.estilo,
            COUNT(i.id)
        )
        FROM Intercambio i
        JOIN i.idRopa r
        GROUP BY r.id, r.nombre, r.talla, r.estilo
        ORDER BY COUNT(i.id) DESC
    """)
    List<RopaTopDto> findTopRopa(Pageable pageable);


    @Query(value = """
    SELECT u.id AS idUsuario, s.nombre_usuario AS nombreUsuario, COUNT(*) AS totalIntercambios
    FROM usuario_perfil u
    JOIN usuario_sesion s ON u.id_auth = s.id
    JOIN (
        SELECT id_usuario_ofertante AS id_usuario FROM intercambio WHERE estado = 'aceptado'
        UNION ALL
        SELECT id_usuario_solicitante AS id_usuario FROM intercambio WHERE estado = 'aceptado'
    ) i ON u.id = i.id_usuario
    GROUP BY u.id, s.nombre_usuario
    ORDER BY totalIntercambios DESC
    LIMIT 1
""", nativeQuery = true)
    UsuarioMasAceptadoDto findUsuarioConMasIntercambiosAceptados();

}
