package com.cris.muroMensajes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutaInicial {

	@GetMapping("/")
	public String rutaInicial() {

		
		return "index";
	}
}
