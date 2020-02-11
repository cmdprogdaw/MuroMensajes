package com.cris.muroMensajes;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutasGenericas {

	@GetMapping("/")
	public String rutaInicial(Authentication authentication) {
		
		return "index";
	}
	
	
	@GetMapping("/login")
	public String seguridad() {
		
		return "start";
	}
	
	
	@GetMapping("/logout")
	public String finalizar(Authentication authentication) {
		
		return "ok";
	}
}
