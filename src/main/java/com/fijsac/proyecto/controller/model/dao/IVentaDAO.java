package com.fijsac.proyecto.controller.model.dao;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fijsac.proyecto.controller.model.entidad.Venta;

public interface IVentaDAO extends CrudRepository<Venta,Long> {
    @Procedure(name = "ingresar_monto_venta")
    Void ingresar_monto_venta(@Param("r") int r);

    @Procedure(name = "editar_monto_venta")
    Void editar_monto_venta(@Param("a") Long a);
}
