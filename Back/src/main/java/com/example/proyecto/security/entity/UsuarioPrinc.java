package com.example.proyecto.security.entity;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sun.istack.NotNull;

public class UsuarioPrinc implements UserDetails {
	
	
	private String nombre;
	
	private String nombreUsuario;
	
	private String email;
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;

	
	public UsuarioPrinc(String nombre, String nombreUsuario, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UsuarioPrinc build(Usuario usuario) {
		List<GrantedAuthority> authorities = 
				usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
		return new UsuarioPrinc(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getPassword(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombreUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}
	
	

}
