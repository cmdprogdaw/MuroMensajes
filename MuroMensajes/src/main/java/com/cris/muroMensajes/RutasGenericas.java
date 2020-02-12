package com.cris.muroMensajes;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cris.muroMensajes.sesiones.Carrito;

@Controller
public class RutasGenericas {

	@GetMapping("/")
	public String rutaInicial(Authentication authentication) {
		
		return "index";
	}
	
	
	@GetMapping("/login")
	public String seguridad(HttpSession sesion) {
		
		sesion.setAttribute("carrito", new Carrito());
		return "start";
	}
	
	
	@GetMapping("/logout")
	public String finalizar(Authentication authentication) {
		
		return "ok";
	}
}
