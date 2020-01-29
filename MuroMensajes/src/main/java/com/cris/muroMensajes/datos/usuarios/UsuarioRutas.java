package com.cris.muroMensajes.datos.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cris.muroMensajes.beans.Encoder;

@Controller
public class UsuarioRutas {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private Encoder encoder;
	
	
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
	public String mensajeAnadir(@ModelAttribute Usuario usuario){
		
		//crear el bean del encoder e inyectarlo donde se necesite
		
//		Encoder passwordEncoder = encoder;
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		
		
		usuarioDAO.save(usuario);
		
		return "redirect:/usuarios";
	}
	
	
	@GetMapping("/usuarios/borrar/{id}")
	public String mensajesBorrar(@PathVariable String id){
		
		usuarioDAO.deleteById(id);	
		
		return "redirect:/usuarios";
	}
	
	
	@GetMapping("/usuario/{id}")
	public String detalle(@PathVariable String id, Model model){
		
		
		Usuario usuario = usuarioDAO.findById(id).get();
		model.addAttribute("usuario", usuario);
		
		
		return "detalleUsuario";
	}
}
