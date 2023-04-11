package com.secondpawshop.init.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.secondpawshop.init.entity.Venta;
import com.secondpawshop.init.entity.VentaLlaveCompuesta;
import org.springframework.data.repository.query.Param;

@Repository
public interface VentaRepository extends JpaRepository <Venta, VentaLlaveCompuesta> {

	@Query (value = "INSERT INTO VENTA (idVenta,idUsuarioPropetario,nombreProducto,idUsuarioComprador,cantidadAComprar,precioTotal,estado)"
			+ " VALUES (:idVenta,:idUsuarioPropetario,:nombreProducto,:idUsuarioComprador,:cantidadAComprar,:precioTotal,'CARRO')"
			,nativeQuery = true)
	
	void addVenta (String idVenta, String idUsuarioPropetario, String nombreProducto, String idUsuarioComprador, int cantidadAComprar, int precioTotal);

	@Query (value = "SELECT IDVENTA FROM VENTA WHERE IDUSUARIOCOMPRADOR =:idUsuarioComprador AND ESTADO = 'CARRO' ORDER BY IDVENTA DESC LIMIT 1")
	String traerIdVentaEnCarrito (@Param ("idUsuarioComprador") String idUsuarioComprador);
	
	@Query (value = "SELECT IDVENTA FROM VENTA WHERE IDUSUARIOCOMPRADOR =:idUsuarioComprador AND ESTADO = 'COMPRADO' ORDER BY IDVENTA DESC LIMIT 1")
	String traerIdVentaComprado (@Param ("idUsuarioComprador") String idUsuarioComprador);
}
