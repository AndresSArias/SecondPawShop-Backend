package com.secondpawshop.init.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.secondpawshop.init.entity.Venta;
import com.secondpawshop.init.entity.dto.VentaDto;
import com.secondpawshop.init.service.VentaService;

@RestController
@CrossOrigin
public class ventaController {
	
	final private VentaService ventaService;

	public ventaController(VentaService ventaService) {
		this.ventaService = ventaService;
	}
	/*
	@PostMapping("/Venta/AgregarAlCarro")
	public ResponseEntity<Venta> agregarAlCarro (@RequestBody VentaDto venta ){
		
		return new ResponseEntity<>(ventaService.agregarAlCarro(venta), HttpStatus.CREATED);
	}
	*/
}
