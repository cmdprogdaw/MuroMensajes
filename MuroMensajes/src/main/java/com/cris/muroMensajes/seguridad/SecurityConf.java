package com.cris.muroMensajes.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cris.muroMensajes.servicios.Autenticacion;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private Autenticacion autenticacion;
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
		//creo un proveedor de autenticaciones 
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	
    	//porque mis password estan encriptadas en mi base de datos mediante este sistema de codificacion
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        
        //y le digo quien es mi servicio que autentica (esta inyectado)
        provider.setUserDetailsService(autenticacion);
    	
        //se lo establezco al manager que construye la seguridad
    	auth.authenticationProvider(provider);
    }
    
	
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .httpBasic();
    }
	*/
}
