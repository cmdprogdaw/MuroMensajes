package com.cris.muroMensajes.sesiones;


//
//@Component("carrito")
//@Scope(	value = WebApplicationContext.SCOPE_SESSION, 
//		proxyMode = ScopedProxyMode.TARGET_CLASS)
//


public class Carrito {
	
	private int productos = 0;
	
	
	
	public int getProductos() {
		
		return productos;
	}
	
	
	public void addProducto() {
		
		productos++;
	}	
}
