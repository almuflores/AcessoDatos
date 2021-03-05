package com.example.proyecto.security.controlador;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.dto.Mensaje;
import com.example.proyecto.security.dto.JwtDto;
import com.example.proyecto.security.dto.Loginusuario;
import com.example.proyecto.security.dto.Nuevousuario;
import com.example.proyecto.security.entity.Rol;
import com.example.proyecto.security.entity.Usuario;
import com.example.proyecto.security.enums.RolNombre;
import com.example.proyecto.security.jwt.JwtProvider;
import com.example.proyecto.security.service.RolService;
import com.example.proyecto.security.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin

public class UsuarioController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Validated @RequestBody Nuevousuario nuevoUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
			return new ResponseEntity(new Mensaje("usuario ya existe"), HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity(new Mensaje("email ya existe"), HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),passwordEncoder.encode(nuevoUsuario.getPassword()));
		
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		if(nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
	
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		
		return new ResponseEntity("Usuario guardado",HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Validated @RequestBody Loginusuario loginUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity("Campos mal puestos", HttpStatus.BAD_REQUEST);
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToke(authentication);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		
		return new ResponseEntity(jwtDto, HttpStatus.OK);
		
	}

}
