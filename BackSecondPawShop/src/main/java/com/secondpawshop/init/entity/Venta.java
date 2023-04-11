package com.secondpawshop.init.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;


@Entity
@Table (name = "VENTA")
@IdClass (VentaLlaveCompuesta.class)
@Access(value=AccessType.FIELD)
public class Venta {
	
	@Id
	@Column (name = "IDVENTA")
	private String idVenta;
	
	private ProductoLlaveCompuesta productoLlaveCompuesta;
	
	@Id
	@Column (name = "IDUSUARIOCOMPRADOR")
	private String idUsuarioComprador;
	
	@Column (name = "CANTIDADACOMPRAR")
	private int cantidadAComprar;
	
	@Column (name = "PRECIOTOTAL")
	private int precioTotal;

	@Column (name = "ESTADO")
	private String estado;
	
	public Venta () {
		
	}

	public Venta(String idVenta, ProductoLlaveCompuesta productoLlaveCompuesta, String idUsuarioComprador,
			int cantidadAComprar, int precioTotal, String estado) {
		super();
		this.idVenta = idVenta;
		this.productoLlaveCompuesta = productoLlaveCompuesta;
		this.idUsuarioComprador = idUsuarioComprador;
		this.cantidadAComprar = cantidadAComprar;
		this.precioTotal = precioTotal;
		this.estado = estado;
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

	public int getCantidadAComprar() {
		return cantidadAComprar;
	}

	public void setCantidadAComprar(int cantidadAComprar) {
		this.cantidadAComprar = cantidadAComprar;
	}

	public int getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(int precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}