package com.secondpawshop.init.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "USUARIO")
public class Usuario {
	
	@Id
	@Column (name = "idUsuario")
	private String idUsuario;
	
	@Column (name = "nombre")
	private String nombre;
	
	@Column (name = "apellido")
	private String apellido;
	
	@Column (name = "correo")
	private String correo;
	
	@Column (name = "celular")
	private String celular;
	
	@Column (name = "direccion")
	private String direccion;
	
	@Column (name = "contrsena")
	private String contrasena;
	
	public Usuario () {
		
	}
	
	

	public Usuario(String idUsuario, String nombre, String apellido, String correo, String celular, String direccion,
			String contrasena) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.celular = celular;
		this.direccion = direccion;
		this.contrasena = contrasena;
	}



	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	

}
