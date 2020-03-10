package com.cris.muroMensajes.datos.usuarios;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cris.muroMensajes.beans.PassEncoder;
import com.cris.muroMensajes.roles.Rol;
import com.cris.muroMensajes.roles.RolDAO;

@Controller
public class UsuarioRutas {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private RolDAO rolDAO;	
	
	@Autowired
	private PassEncoder passEncoder;

	
	@GetMapping("/consultas")
	public String consultas() {
		
        /******* IMPORTANTE PARA MI PROYECTO *********/
		//filtro edad
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findByEdad(10);
		
		//filtro edad menor que
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findByEdadLessThan(30);
		
		//filtro edad menor o igual que
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findByEdadLessThanEqual(30);
		
		//edad y usuario
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findByEdadAndUsuario(10, "cris");
		
		//edad o usuario
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findByEdadOrUsuario(10, "nata");
		
		
		//busca por usuario que el nombre tenga dentro esa cadena
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findByUsuarioContaining("a");
		
		//termina por esa cadena
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findByUsuarioEndsWith("a");
		
		//termina por esa cadena
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findByUsuarioStartsWith("r");
		
		
		//da como mucho los 2 que encuentre por esa edad 
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findTop2ByEdad(30);
		
		//da los mayores de esa edad 
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findByEdadGreaterThan(10);
		
		//da como mucho los 2 que encuentre mayores de esa edad 
		//List<Usuario> resultado = (List<Usuario>)usuarioDAO.findTop2ByEdadGreaterThan(0);
		
		//System.out.println(resultado);
		
		//cuenta los que tienen 10 años
		//Integer cuantos = usuarioDAO.countByEdad(10);
		//System.out.println(cuantos);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/usuarios")
	public ModelAndView todosLosUsuarios(HttpSession sesion) {
		
		
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
		
		
		usuario.setPassword(passEncoder.encoder().encode(usuario.getPassword()));
		
		usuarioDAO.save(usuario);
		
		return "redirect:/usuarios";
	}
	

	
	@PostMapping("/usuarios/editar")
	public ModelAndView usuariosEditar(
			
			@Valid @ModelAttribute("user") Usuario usuario,
			BindingResult bindingResult) {

		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			
			mav.setViewName("editarUser");
			
			List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
			mav.addObject("roles",listaRoles);
			
			return mav;
		}
		
		usuarioDAO.save(usuario);
		mav.setViewName("redirect:/usuarios");
		return mav;
	}	

	
	@GetMapping("/usuarios/editar/{id}")
	public ModelAndView usuariosEditar(@PathVariable String id, Authentication authentication) {
		
		ModelAndView mav = new ModelAndView();
		
		String quien = authentication.getName();
		List<GrantedAuthority> autoridades = (List<GrantedAuthority>) authentication.getAuthorities();
		
		//System.out.println(autoridades);
		
		
		for (GrantedAuthority autoridad : autoridades) {
			//System.out.println(autoridad.getAuthority());
		
			if(!quien.equalsIgnoreCase(id) && !autoridad.getAuthority().equals("ADMIN") && !autoridad.getAuthority().equals("MODERADOR")) {
				mav.setViewName("redirect:/usuarios");
				return mav;
			}
		}
		
		
		Usuario user = usuarioDAO.findById(id).get();
		
		mav.setViewName("editarUser");
		mav.addObject("user",user);
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		mav.addObject("roles",listaRoles);
		
		return mav;
	}	
	

	
	
	

	@GetMapping("/usuarios/borrar/{id}")
	public String usuariosBorrar(@PathVariable String id, Authentication authentication) {
		

		
		String quien = authentication.getName();
		List<GrantedAuthority> autoridades = (List<GrantedAuthority>) authentication.getAuthorities();
		
		
		
		for (GrantedAuthority autoridad : autoridades) {
		
			if(!quien.equalsIgnoreCase(id) && !autoridad.getAuthority().equals("ADMIN")) {
				
				return "redirect:/usuarios";
			}
		}
		
		usuarioDAO.deleteById(id);
		
		return "redirect:/usuarios";
	}
	
	
	
}
