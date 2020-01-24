package com.cris.muroMensajes.datos.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioRutas {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@GetMapping("/usuarios")
	public ModelAndView todosLosUsuarios(){
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuarios");
		mav.addObject("usuario", new Usuario());
		
		List<Usuario> listaUsuarios = (List<Usuario>)usuarioDAO.findAll();
		mav.addObject("usuarios", listaUsuarios);
		
		return mav;
	}
	
	
	@PostMapping("/usuarios/anadir")
	public String mensajeAnadir(@ModelAttribute Usuario listaUsuarios){
		
		usuarioDAO.save(listaUsuarios);
		
		return "redirect:/usuarios";
	}
	
	
	@GetMapping("/usuarios/borrar/{id}")
	public String mensajesBorrar(@PathVariable long id){
		
		
		usuarioDAO.deleteById(id);
		
		
		return "redirect:/usuarios";
	}
}
