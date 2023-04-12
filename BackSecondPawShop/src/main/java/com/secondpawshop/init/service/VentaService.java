package com.secondpawshop.init.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.secondpawshop.init.entity.Producto;
import com.secondpawshop.init.entity.ProductoId;
import com.secondpawshop.init.entity.Usuario;
import com.secondpawshop.init.entity.Venta;
import com.secondpawshop.init.entity.VentaId;
import com.secondpawshop.init.entity.dto.VentaCarroBackendDto;
import com.secondpawshop.init.entity.dto.VentaDto;
import com.secondpawshop.init.repository.IdCarroRepository;
import com.secondpawshop.init.repository.IdCompradoRepository;
import com.secondpawshop.init.repository.ProductoRepository;
import com.secondpawshop.init.repository.UsuarioRepository;
import com.secondpawshop.init.repository.VentaRepository;
@Service
public class VentaService {
	
	final private VentaRepository ventaRepository;
	final private ProductoRepository repoProducto;
	final private UsuarioRepository repoUsuario;
	final private IdCarroRepository repoIdCarro;
	final private IdCompradoRepository repoIdComprado;
	
	public VentaService(VentaRepository ventaRepository, ProductoRepository repoProducto, UsuarioRepository repoUsuario, IdCarroRepository repoIdCarro, IdCompradoRepository repoIdComprado) {
		this.ventaRepository = ventaRepository;
		this.repoProducto = repoProducto;
		this.repoUsuario = repoUsuario;
		this.repoIdCarro = repoIdCarro;
		this.repoIdComprado = repoIdComprado;
	}

	public Venta agregarAlCarro(VentaDto ventaDto) {
		
		VentaId id = new VentaId(traerIdVenta(ventaDto.getIdUsuarioComprador()), ventaDto.getIdUsuarioPropetario(),ventaDto.getNombreProducto(),
				ventaDto.getIdUsuarioComprador());
		
		ProductoId productoId = new ProductoId(ventaDto.getIdUsuarioPropetario(), ventaDto.getNombreProducto());
		Optional<Producto> producto = repoProducto.findById(productoId);
		
		Optional<Usuario> usuario = repoUsuario.findById(ventaDto.getIdUsuarioComprador());
		
		Venta venta = new Venta(id,  producto.get(),  usuario.get(), ventaDto.getCantidadAComprar(), ventaDto.getPrecioTotal(), "CARRO");
		
		ventaRepository.save(venta);
		
		return venta;
	}
	
	private String traerIdVenta(String idUsuarioComprador) {
		
		String idVenta = repoIdCarro.getIdCarro(idUsuarioComprador);
		
		if (idVenta == null) {
			idVenta = repoIdComprado.getIdComprado(idUsuarioComprador);
			
			if (idVenta == null) {
				idVenta = "1";
			}else {
				idVenta = ""+ (Integer. parseInt(idVenta)+1);
			}
			
			
		}
		return idVenta;
	}

	public void cancelarProductoEnCarrito(VentaCarroBackendDto ventaCancelarCarroDto) {
		
		String IdVenta = repoIdCarro.getIdCarro(ventaCancelarCarroDto.getIdusuariocomprador());
		
		VentaId id = new VentaId (IdVenta, ventaCancelarCarroDto.getIdusuariopropetario(), ventaCancelarCarroDto.getNombreproducto(),ventaCancelarCarroDto.getIdusuariocomprador());
		
		Optional<Venta> ventaOptional = ventaRepository.findById(id);
		
		if (ventaOptional.isPresent()) {
	        Venta venta = ventaOptional.get();
	        venta.setEstado("CANCELADO");
	        ventaRepository.save(venta);
	    }
	}

	public void comprarProductoEnCarrito(VentaCarroBackendDto ventaComprarCarroDto) {
		
		String IdVenta = repoIdCarro.getIdCarro(ventaComprarCarroDto.getIdusuariocomprador());
		
		VentaId id = new VentaId (IdVenta, ventaComprarCarroDto.getIdusuariopropetario(), ventaComprarCarroDto.getNombreproducto()
				,ventaComprarCarroDto.getIdusuariocomprador());
		
		Optional<Venta> ventaOptional = ventaRepository.findById(id);
		
		if (ventaOptional.isPresent()) {
	        Venta venta = ventaOptional.get();
	        venta.setEstado("COMPRADO");
	        ventaRepository.save(venta);
	    }
	}
	
}
