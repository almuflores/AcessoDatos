package com.example.proyecto.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyecto.security.entity.Rol;
import com.example.proyecto.security.entity.Usuario;
import com.example.proyecto.security.enums.RolNombre;

public interface RolRepository extends JpaRepository<Rol, Integer>{
	Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
