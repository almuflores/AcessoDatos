package com.example.proyecto.security.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyecto.security.entity.*;


public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByNombreUsuario(String nombreusuario);
	boolean existsByNombreUsuario(String nombreusuario);
	boolean existsByEmail(String emial);
}
