package com.fijsac.proyecto.controller.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fijsac.proyecto.controller.model.entidad.Proveedor;

public interface IProveedorDAO extends CrudRepository<Proveedor,Long> {
    public List<Proveedor> findAllByOrderByNombre();
    @Procedure(name = "regist_op_hist")
    Void regist_op_hist(
        @Param("operacion") String operacion,
        @Param("codigo") String codigo
    );
}
