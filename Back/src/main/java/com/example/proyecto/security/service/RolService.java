package com.example.proyecto.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.proyecto.security.entity.Rol;
import com.example.proyecto.security.enums.RolNombre;
import com.example.proyecto.security.repository.RolRepository;

@Service
@Transactional
public class RolService {
	
	@Autowired
	RolRepository rolRepository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return rolRepository.findByRolNombre(rolNombre);
	}
	public void save(Rol rol) {
		rolRepository.save(rol);
	}

}
