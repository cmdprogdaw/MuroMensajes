package com.cris.muroMensajes.datos.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cris.muroMensajes.datos.roles.Rol;
import com.cris.muroMensajes.datos.roles.RolDAO;


@Controller
public class UsuarioRutas {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private RolDAO rolDAO;
	
	
	@GetMapping("/usuarios")
	public ModelAndView todosLosUsuarios() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuarios");
		mav.addObject("user",new Usuario());
		
		List<Usuario> listaUsuarios = (List<Usuario>)usuarioDAO.findAll();
		mav.addObject("usuarios",listaUsuarios);
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		mav.addObject("roles",listaRoles);

		return mav;
	}
	
	
	
	@PostMapping("/usuarios/anadir")
	public String usuariosAnadir(@ModelAttribute Usuario usuario) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		usuarioDAO.save(usuario);
		
		return "redirect:/usuarios";
	}
	
	

	
	@GetMapping("/usuarios/editar/{id}")
	public ModelAndView usuariosEditar(@PathVariable String id) {
		
		Usuario user = usuarioDAO.findById(id).get();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editarUser");
		mav.addObject("user",user);
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		mav.addObject("roles",listaRoles);
		
		return mav;
	}	
	


	@GetMapping("/usuarios/borrar/{id}")
	public String usuariosBorrar(@PathVariable String id) {
		
		usuarioDAO.deleteById(id);
		
		return "redirect:/usuarios";
	}
}
