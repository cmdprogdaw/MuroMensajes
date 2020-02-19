package com.cris.muroMensajes.datos.usuarios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario,String>{
	
	//?1 es el primer parametro (userViejo) y ?2 el segundo (userNuevo) 
	@Transactional @Query(value="UPDATE usuario u SET nombre='?2' WHERE u.nombre='?1", nativeQuery=true)
	void actualizarNombreUsuario(String userViejo, String userNuevo);
	
	@Transactional @Query(value="SELECT * FROM usuarios WHERE edad=:edad", nativeQuery=true)
	void buscarPorEdad(@Param("edad") Integer anios);
	
	
	//JPQL
	@Query(value="from usuarios where edad=:edad")
	void buscarPorEdad2(@Param("edad") Integer anios);
	
	
	
	/****estas operaciones devuelven una lista de usuarios que busca por (attr) es tipo filtro***/
	
	//por edad
	List<Usuario> findByEdad(Integer edad);
	
	//edad menor que
	List<Usuario> findByEdadLessThan(Integer edad);
	
	//edad menor o igual que
	List<Usuario> findByEdadLessThanEqual(Integer edad);
	
	//dos valores: edad y usuario
	List<Usuario> findByEdadAndUsuario(Integer edad, String usuario);
	
	//por edad o usuario
	List<Usuario> findByEdadOrUsuario(Integer edad, String usuario);
	
	
	//busca por usuario que el nombre tenga dentro algo
	List<Usuario> findByUsuarioContaining(String cadena);
	
	//termina por
	List<Usuario> findByUsuarioEndsWith(String cadena);
	
	//empieza por
	List<Usuario> findByUsuarioStartsWith(String cadena);

	
	//da como mucho 2 que cumplan con ese criterio
	List<Usuario> findTop2ByEdad(Integer edad);
	
	//da como mucho 2 que sean mayor que lo que pongas
	List<Usuario> findTop2ByEdadGreaterThan(Integer edad);
	
	//mayores de la edad
	List<Usuario> findByEdadGreaterThan(Integer edad);
	
	
	
	/**********************/
	//cuenta los que tienen esa edad
	Integer countByEdad(Integer edad);
}
