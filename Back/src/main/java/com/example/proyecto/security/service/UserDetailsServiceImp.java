package com.example.proyecto.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.proyecto.security.entity.Usuario;
import com.example.proyecto.security.entity.UsuarioPrinc;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	UsuarioService usuarioService;
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
		return UsuarioPrinc.build(usuario);
	}

}
