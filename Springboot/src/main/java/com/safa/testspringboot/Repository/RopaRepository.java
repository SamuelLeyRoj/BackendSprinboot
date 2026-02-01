package com.safa.testspringboot.Repository;

import com.safa.testspringboot.Models.Estilo;
import com.safa.testspringboot.Models.Ropa;
import com.safa.testspringboot.Models.Talla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RopaRepository extends JpaRepository<Ropa,Integer> {

    @Query("""
        SELECT r FROM Ropa r
        WHERE (:estilo IS NULL OR r.estilo = :estilo)
        AND (:talla IS NULL OR r.talla = :talla)
        AND (:estado IS NULL OR r.estado = :estado)
        """)
    List<Ropa> filtrar(Estilo estilo, Talla talla, String estado);

}
