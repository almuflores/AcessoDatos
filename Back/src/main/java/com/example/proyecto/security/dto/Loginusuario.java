package com.example.proyecto.security.dto;

import org.hibernate.validator.constraints.NotBlank;

public class Loginusuario {
	
	@NotBlank
	private String nombreUsuario;

	@NotBlank
	private String password;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
