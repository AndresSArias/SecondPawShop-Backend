package com.secondpawshop.init.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;

public class VentaLlaveCompuesta implements Serializable {
	
	String idVenta;
	
	ProductoLlaveCompuesta productoLlaveCompuesta;
	
	String idUsuarioComprador;
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof VentaLlaveCompuesta)) return false;
	    VentaLlaveCompuesta other = (VentaLlaveCompuesta) obj;
	    return Objects.equals(idVenta, other.idVenta) &&
	            Objects.equals(productoLlaveCompuesta, other.productoLlaveCompuesta) &&
	            Objects.equals(idUsuarioComprador, other.idUsuarioComprador);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(idVenta, productoLlaveCompuesta, idUsuarioComprador);
	}

	
	public VentaLlaveCompuesta () {}

	public VentaLlaveCompuesta(String idVenta, ProductoLlaveCompuesta productoLlaveCompuesta,
			String idUsuarioComprador) {
		super();
		this.idVenta = idVenta;
		this.productoLlaveCompuesta = productoLlaveCompuesta;
		this.idUsuarioComprador = idUsuarioComprador;
	}

	public String getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(String idVenta) {
		this.idVenta = idVenta;
	}

	public ProductoLlaveCompuesta getProductoLlaveCompuesta() {
		return productoLlaveCompuesta;
	}

	public void setProductoLlaveCompuesta(ProductoLlaveCompuesta productoLlaveCompuesta) {
		this.productoLlaveCompuesta = productoLlaveCompuesta;
	}

	public String getIdUsuarioComprador() {
		return idUsuarioComprador;
	}

	public void setIdUsuarioComprador(String idUsuarioComprador) {
		this.idUsuarioComprador = idUsuarioComprador;
	}
	
	
	
}
