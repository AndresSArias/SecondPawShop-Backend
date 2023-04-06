package com.secondpawshop.init.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.secondpawshop.init.repository.ProductoRepository;
import com.secondpawshop.init.entity.Producto;
import com.secondpawshop.init.entity.ProductoLlaveCompuesta;
import com.secondpawshop.init.entity.dto.ProductoDto;

@Service
public class ProductService {
	
	final private ProductoRepository productoRepository;
	
	public ProductService (ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	
	public List <Producto> getProductoPublicado () {
		return productoRepository.getProductoPublicado();
	}

	public List <Producto> getProductoVerificando () {
		return productoRepository.getProductoVerificando();
	}
	
	public void actualizarProducto(ProductoLlaveCompuesta llaveCompuesta) {
		productoRepository.actualizarProducto(llaveCompuesta);
    }
	
	public void save(Producto producto) {
		productoRepository.save(producto);
	}
	
	public List <Producto> getProductFromCategory (String categoria) {
		return productoRepository.getProductoFromCategoria(categoria);
	}
	
	public ProductoDto findByIdUsuarioFKAndNombre(ProductoLlaveCompuesta llaveCompuesta) {
		
		Optional<Producto> producto = productoRepository.findByIdUsuarioFKAndNombre(llaveCompuesta.getIdUsuarioFK(), llaveCompuesta.getNombre());
		ProductoDto productoDto = new ProductoDto();
		if (producto.isPresent()) {
			productoDto = new ProductoDto(producto.get().getIdUsuarioFK(), producto.get().getNombre(),producto.get().getCategoria(), producto.get().getDescripcion(), producto.get().getCantidad(), producto.get().getPrecio(), producto.get().getImagen());
		}
		return productoDto;
	}
	
}
