package com.safa.testspringboot.Repository;

import com.safa.testspringboot.Models.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones,Integer> {
}
