package com.cris.muroMensajes.roles;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cris.muroMensajes.datos.usuarios.Usuario;

@Entity
public class Rol   {
	
	@Id
	private String nombre = "USER";
	
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "rol")
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	
/*
	@PreUpdate
	@PostUpdate
	@PreDestroy 
	@PrePersist
	@PostPersist
	 
	
	//antes de que se borre el rol, ejecuta esto
	@PreDestroy
	public void reasignarRolesUsuario() {
		
		//recorrer la lista de usuarios reasignando los roles
		for(Usuario user: usuarios) {		
			
			user.setRoles(null);
		}
		
		//tambien vale esta otra forma
		//usuarios.forEach(user->user.setRoles(null));
		 
	}
*/
	
	
	public void addUsuario(Usuario usuario) {

		if(!usuarios.contains(usuario)) {
			
			usuarios.add(usuario);
		}
	}	




	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Rol [rol=" + nombre + "]";
	}

	
	
	
}
