package com.cris.muroMensajes.datos.roles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolDAO extends CrudRepository<Rol,String>{

}
