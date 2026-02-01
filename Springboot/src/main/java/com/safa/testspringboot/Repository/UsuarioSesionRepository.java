package com.safa.testspringboot.Repository;

import com.safa.testspringboot.Models.UsuarioSesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioSesionRepository extends JpaRepository<UsuarioSesion,Integer> {

    Optional<UsuarioSesion> findByNombre(String nombre);
}
