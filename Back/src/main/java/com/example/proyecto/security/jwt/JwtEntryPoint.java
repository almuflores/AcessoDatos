package com.example.proyecto.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


//Comprobar las credenciales en el loggin

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{

	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	
	
	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException e) throws IOException, ServletException {
		
		//Para ver donde está el error
		logger.error("Fail en el metodo commence");
		
		res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
		
	}

}
