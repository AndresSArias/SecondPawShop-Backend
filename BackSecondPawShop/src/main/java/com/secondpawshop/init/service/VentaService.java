package com.secondpawshop.init.service;

import com.secondpawshop.init.entity.Venta;
import com.secondpawshop.init.entity.dto.VentaDto;
import com.secondpawshop.init.repository.VentaRepository;

public class VentaService {
	
	final private VentaRepository ventaRepository;
	
	public VentaService(VentaRepository ventaRepository) {
		this.ventaRepository = ventaRepository;
	}

	public Venta agregarAlCarro(VentaDto ventaDto) {
		Venta venta = new Venta(traerIdVenta(ventaDto.getIdUsuarioComprador()), ventaDto.getIdUsuarioPropetario()
				, ventaDto.getNombreProducto(), ventaDto.getIdUsuarioComprador(), ventaDto.getCantidadAComprar(), ventaDto.getPrecioTotal(), "CARRO");
		
		ventaRepository.save(venta);
		
		return venta;
	}

	private String traerIdVenta(String idUsuarioComprador) {
		
		String idVenta = ventaRepository.traerIdVentaEnCarrito(idUsuarioComprador);
		
		if (idVenta.isEmpty()) {
			idVenta = ventaRepository.traerIdVentaComprado(idUsuarioComprador);
			idVenta = ""+ (Integer. parseInt(idVenta)+1);
		}
		return idVenta;
	}

}
