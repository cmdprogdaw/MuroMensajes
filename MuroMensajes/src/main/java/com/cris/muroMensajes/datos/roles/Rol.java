package com.cris.muroMensajes.datos.roles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.cris.muroMensajes.datos.usuarios.Usuario;

@Entity
public class Rol {

	@Id
	private String nombre;
	
	
	@ManyToMany(mappedBy="roles")
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public void add(Usuario usuario) {

		if(!usuarios.contains(usuario)) {
			
			usuarios.add(usuario);
			usuario.addRoles(this);
		}
		
	}

	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
