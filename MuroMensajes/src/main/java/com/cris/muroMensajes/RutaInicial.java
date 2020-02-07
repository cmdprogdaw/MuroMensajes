package com.cris.muroMensajes;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutaInicial {

	@GetMapping("/")
	public String rutaInicial(Authentication authentication) {
		
		System.out.println(authentication.isAuthenticated());
		System.out.println(authentication.getName());
		List<GrantedAuthority> permisos = (List<GrantedAuthority>)authentication.getAuthorities();
		
		return "index";
	}
}
