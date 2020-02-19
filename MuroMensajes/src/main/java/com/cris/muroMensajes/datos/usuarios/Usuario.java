package com.cris.muroMensajes.datos.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cris.muroMensajes.roles.Rol;

@Entity
public class Usuario implements UserDetails  {

	
	@Id
	private String usuario;
	
	@Column
	private String password;
	
	@Column
	@Size(min=5, message="el nombre no puede ser tan pequeño")
	@Size(max=25, message="el nombre no puede ser tan largo")
	@NotNull(message="no puedes dejar esto vacio")
	private String nombre;
	
	@Column
	private String apellidos;
	
	@Column
	@Pattern(regexp="[A-Za-z0-9._-]+@[A-Za-z.]+",message="email invalido")
	private String email;

	@Column
	private Integer edad;
	
	
	@ManyToOne
	private Rol rol = new Rol();	
	
	
	
	
	@PreUpdate
	public void antesDeUpdate() {
		
		System.out.println(this);
	}
	
	
	@PostUpdate
	public void despuesDeUpdate() {
		
		System.out.println(this);
	}
	
	
	
	
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	    grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre()));
	    	    
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

	
	@Override
	public String toString() {
		return "[usuario=" + usuario + ", edad=" + edad + "]";
	}
	
	
}
