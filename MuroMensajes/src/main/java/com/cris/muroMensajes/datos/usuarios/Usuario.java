package com.cris.muroMensajes.datos.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cris.muroMensajes.datos.roles.Rol;

@Entity
public class Usuario implements UserDetails{

	@Id
	private String usuario;
	
	@Column
	private String password;
	
	@Column
	private String nombre;
	
	@Column
	private String apellidos;
	
	@Column
	private String email;
	
	@Column
	private Integer telefono;

	
	
	@ManyToMany(fetch=FetchType.EAGER) //carga todas las relaciones
	@JoinTable(name = "permisos", //la tabla que hace join se llama permisos
			  joinColumns = @JoinColumn(name = "FK_usuarios"),  //la tabla en mi lado
			  inverseJoinColumns = @JoinColumn(name = "FK_roles"))
	private List<Rol> roles = new ArrayList<Rol>();	
	
	
	
	
	
	private boolean estaUnRol(String unRol) {
		
		boolean esta = false;
		ListIterator<Rol>  it = roles.listIterator();
		while((it.hasNext())&&(!esta)) {
			
			Rol rol = it.next();
			if(rol.getRol().matches(unRol)) esta = true;
		}
		return esta;
	}
	
	
	public void addRol(String unRol) {
		
		if(!estaUnRol(unRol)) {
			
			Rol rol = new Rol();
			rol.setRol(unRol);
			rol.addUsuario(this);
			
			roles.add(rol);
		}
	}
	
	
	
	

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}


	

	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	    for (Rol rol : roles){
	        grantedAuthorities.add(new SimpleGrantedAuthority(rol.getRol()));
	    }
	    
	    return grantedAuthorities;
		
	}

	@Override
	public String getUsername() {
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
