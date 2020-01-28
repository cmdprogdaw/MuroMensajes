package com.cris.muroMensajes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cris.muroMensajes.datos.usuarios.UsuarioDAO;

@Service
public class Autenticacion implements UserDetailsService{

	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return usuarioDAO.findById(username).get();
	}

}
