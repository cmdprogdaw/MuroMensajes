package com.cris.muroMensajes;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.cris.muroMensajes.sesiones.Carrito;

@Controller
public class RutasGenericas {

	
	@GetMapping("/")
	public String inicial(HttpSession sesion, ModelMap model, 
			@ModelAttribute Carrito carrito) {
		
		
		sesion.setAttribute("carrito", carrito);
		return "index";
	}
	
	
	@GetMapping("/addCarrito")
	public String carrito(HttpSession sesion, @SessionAttribute Carrito carrito) {
		
		//Carrito carrito = (Carrito)sesion.getAttribute("carrito");
		System.out.println(carrito);
		if(carrito!=null) {
			carrito.addProducto();
			
		}
		
		return "redirect:/";
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
