package com.secondpawshop.init.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.secondpawshop.init.repository.UsuarioRepository;
import com.secondpawshop.init.entity.Usuario;
import com.secondpawshop.init.entity.dto.UsuarioLoginDto;

@Service
public class UserService {
	
	final private UsuarioRepository usuarioRepository;
	
	public UserService (UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List <Usuario> getUsers (){
		return usuarioRepository.getUsuarios();
	}
	
	public void save(Usuario cliente) {
		usuarioRepository.save(cliente);
	}
	
	public Usuario getLogin (UsuarioLoginDto dataLogin) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(dataLogin.getIdUsuario());
		if (usuario.isPresent()) {
			if (usuario.get().getContrasena().equals(dataLogin.getContrasena())){
				return usuario.get();
			}
		}
		return null;
	}
	
	
	
}
