package com.secondpawshop.init.controller;

import org.springframework.web.bind.annotation.RestController;

import com.secondpawshop.init.service.UserService;
import com.secondpawshop.init.entity.Usuario;
import com.secondpawshop.init.entity.dto.UsuarioDto;
import com.secondpawshop.init.entity.dto.UsuarioLoginDto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class usuarioController {
	
	final private UserService usuarioService;
	
	public usuarioController (UserService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping ("/Usuarios")
	public List <Usuario> getUsuarios(){
		return usuarioService.getUsers();
	}
	
	@GetMapping ("/prueba")
	public String getPrueba() {
		return "Hola Mundo deploy";
	}
	
	@PostMapping("/Usuario/registrar")
	public ResponseEntity<Usuario> save(@RequestBody UsuarioDto c) {
		
		Usuario cliente = new Usuario (c.getIdUsuario(), c.getNombre(), c.getApellido()
				, c.getCorreo(),c.getCelular(), c.getDireccion(),c.getContrasena(), "CLIENTE");
		
		usuarioService.save(cliente);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/Usuario/login")
	public Usuario getCredencial (@RequestBody UsuarioLoginDto dataLogin) {
		return usuarioService.getLogin(dataLogin);
	}
	
	
}
