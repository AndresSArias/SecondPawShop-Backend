package com.secondpawshop.init.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.secondpawshop.init.entity.Producto;
import com.secondpawshop.init.entity.ProductoLlaveCompuesta;

@Repository
public interface ProductoRepository extends JpaRepository <Producto, ProductoLlaveCompuesta>{
	
	@Query (value = "INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado)"
			+" VALUES (:idUsuarioFK,:nombre,:categoria,:descripcion,:cantidad,:precio, :imagen,'VERIFICANDO')"
			,nativeQuery = true)
	void addProduct (String idUsuarioFK, String nombre, String categoria, String descripcion, int cantidad, int precio, String imagen);
}
