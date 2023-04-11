package com.secondpawshop.init.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;


public class ProductoLlaveCompuesta implements Serializable{
	
	@Column (name = "IDUSUARIOFK")
	private String idUsuarioFK;
	
	@Column (name = "NOMBRE")
	private String nombre;
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProductoLlaveCompuesta)) return false;
        ProductoLlaveCompuesta that = (ProductoLlaveCompuesta) obj;
        return Objects.equals(getIdUsuarioFK(), that.getIdUsuarioFK()) &&
               Objects.equals(getNombre(), that.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUsuarioFK(), getNombre());
    }
	
	public ProductoLlaveCompuesta() {
		
	}
	
	public ProductoLlaveCompuesta(String idUsuarioFK, String nombre) {
		super();
		this.idUsuarioFK = idUsuarioFK;
		this.nombre = nombre;
	}
	
	public String getIdUsuarioFK() {
		return idUsuarioFK;
	}

	public void setIdUsuarioFK(String idUsuarioFK) {
		this.idUsuarioFK = idUsuarioFK;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
