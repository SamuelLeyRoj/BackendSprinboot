package com.safa.testspringboot.Repository;

import com.safa.testspringboot.Models.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil,Integer> {
}
