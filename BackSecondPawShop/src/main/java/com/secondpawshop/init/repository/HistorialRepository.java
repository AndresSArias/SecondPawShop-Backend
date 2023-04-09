package com.secondpawshop.init.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.secondpawshop.init.entity.dto.VentaHistorialDto;
import com.secondpawshop.init.entity.view.ViewHistorial;

public interface HistorialRepository extends JpaRepository<ViewHistorial, String> {

	Optional<ViewHistorial> findByIdUsuarioComprador(String password);
	
	@Query (value = "SELECT * FROM VISTAHISTORIAL WHERE IDUSUARIOCOMPRADOR = :idUsuarioComprador", nativeQuery = true)
	List <ViewHistorial> getHistorial(@Param("idUsuarioComprador")String idUsuarioComprador);

}