package com.secondpawshop.init.service;

import org.springframework.stereotype.Service;

import com.secondpawshop.init.entity.ProductoLlaveCompuesta;
import com.secondpawshop.init.entity.Venta;
import com.secondpawshop.init.entity.dto.VentaDto;
import com.secondpawshop.init.repository.VentaRepository;
@Service
public class VentaService {
	
	final private VentaRepository ventaRepository;
	
	public VentaService(VentaRepository ventaRepository) {
		this.ventaRepository = ventaRepository;
	}

	public Venta agregarAlCarro(VentaDto ventaDto) {
		/*
		Venta venta = new Venta(traerIdVenta(ventaDto.getIdUsuarioComprador()), ventaDto.getIdUsuarioPropetario()
				, ventaDto.getNombreProducto(), ventaDto.getIdUsuarioComprador(), ventaDto.getCantidadAComprar(), ventaDto.getPrecioTotal(), "CARRO");
		*/
		ProductoLlaveCompuesta productoLlaveCompuesta = new ProductoLlaveCompuesta(ventaDto.getIdUsuarioPropetario(), ventaDto.getNombreProducto());
		Venta venta = new Venta(traerIdVenta(ventaDto.getIdUsuarioComprador()),productoLlaveCompuesta , ventaDto.getIdUsuarioComprador(),
				ventaDto.getCantidadAComprar(), ventaDto.getPrecioTotal(), "CARRO");
		
		ventaRepository.save(venta);
		
		return null;
	}
	
	private String traerIdVenta(String idUsuarioComprador) {
		
		String idVenta = ventaRepository.traerIdVentaEnCarrito(idUsuarioComprador).getIdVenta();
		
		if (idVenta.isEmpty()) {
			idVenta = ventaRepository.traerIdVentaComprado(idUsuarioComprador).getIdVenta();
			
			if (idVenta.isEmpty()) {
				idVenta = "1";
			}else {
				idVenta = ""+ (Integer. parseInt(idVenta)+1);
			}
			
			
		}
		return idVenta;
	}
	
}
