package com.secondpawshop.init.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secondpawshop.init.service.ProductService;
import com.secondpawshop.init.entity.Producto;
import com.secondpawshop.init.entity.ProductoLlaveCompuesta;
import com.secondpawshop.init.entity.dto.ProductoDto;

@RestController
@CrossOrigin
public class productoController {
	
	final private ProductService productoService;
	
	public productoController (ProductService productoService) {
		this.productoService = productoService;
	}
	
	@GetMapping("/Producto/Publicado")
	public List <Producto> getProductosPublicados (){
		return productoService.getProductoPublicado();
	}

	@GetMapping("/Producto/Verificando")
	public List <Producto> getProductoVerificando (){
		return productoService.getProductoVerificando();
	}
	/*
	@PutMapping("/Producto/VerificandoToPublicado")
    public ResponseEntity<Void> actualizarCampo(@RequestBody String idUsuarioFK, @RequestBody String nombre) {
		productoService.actualizarProducto(idUsuarioFK, nombre);
        return ResponseEntity.noContent().build();
    }
	*/
	
	@PutMapping("/Producto/VerificandoToPublicado")
	public ResponseEntity<Void> actualizarProducto(@RequestBody ProductoLlaveCompuesta llaveCompuesta) {
		productoService.actualizarProducto(llaveCompuesta);
	    return ResponseEntity.noContent().build();
	}
	
	/*
	@PostMapping("/Producto/Crear")
	public ResponseEntity<Producto> crearProducto (@RequestBody Producto producto){
		producto.setEstado("VERIFICANDO");
		productoService.save(producto);
		return new ResponseEntity<>(producto, HttpStatus.CREATED);
	}
	*/
	
	@PostMapping("/Producto/Crear")
	public ResponseEntity<Producto> crearProducto (@RequestBody ProductoDto p ){
		Producto producto = new Producto(p.getIdUsuarioFK(),p.getNombre(), p.getCategoria(), p.getDescripcion(),
				p.getCantidad(), p.getPrecio(), p.getImagen(), "VERIFICANDO");
		productoService.save(producto);
		return new ResponseEntity<>(producto, HttpStatus.CREATED);
	}
	
	@GetMapping("/Producto/{categoria}")
	public List <Producto> getProductFromCategory (@PathVariable ("categoria")String categoria){
		return productoService.getProductFromCategory(categoria);
	}
	
	@GetMapping("/Producto")
	public ResponseEntity<ProductoDto> getProducto(@RequestBody ProductoLlaveCompuesta llaveCompuesta) {
		ProductoDto cliente = productoService.findByIdUsuarioFKAndNombre(llaveCompuesta);
		return new ResponseEntity<ProductoDto>(cliente, HttpStatus.OK);
	}
	
	@DeleteMapping("Producto/Eliminar/{idUsuarioFK}/{nombre}")
    public void eliminarProducto(@PathVariable("idUsuarioFK") String idUsuarioFK, @PathVariable("nombre") String nombre) {
        productoService.eliminarProducto(idUsuarioFK, nombre);
    }
}
