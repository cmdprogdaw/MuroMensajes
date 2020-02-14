package com.cris.muroMensajes.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
    
	
	 @Override //solo protejo el las rutas, no los recursos
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests() //si esta autorizacion de peticion de ruta encaja con (antMatchers):
        	.antMatchers("/usuarios").permitAll() //permite a todo el mundo (incluso quien no este autenticado)
        	//.antMatchers("/mensajes/**").authenticated() //entra cualquiera dentro de los roles > necesita autenticacion
        	.antMatchers("/usuarios/**").hasAuthority("ADMIN") // /usuarios/*cualquier cosa* > tiene q ser administrador
        	.antMatchers("/usuarios/anadir").hasAnyAuthority("ADMIN","MODERADOR") //administrador y moderador
        	.antMatchers("/mensajes/borrar/**/").hasAnyAuthority("ADMIN","MODERADOR")
	        .and()    	
        .formLogin()
            .loginPage("/login").permitAll() //permitir que aqui entre todo el mundo 
            .defaultSuccessUrl("/") //si me permite entrar, la pagina a la que me lleva es la de /
            .failureUrl("/login?error=true") //y si falla le digo que mi pagina de error es la misma que de login pero mandando un parametro
            .usernameParameter("username") //en el formulario que te manda de login el usuario va a estra en un campo y se va a llamar asi
            .passwordParameter("password") //lo mismo que esto ^^
            .and()
        .logout()
            .permitAll()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .and()
        .csrf().disable(); //sistema que sirve para prevenir el phishing (por defecto estaba habilitado)

    }
}
