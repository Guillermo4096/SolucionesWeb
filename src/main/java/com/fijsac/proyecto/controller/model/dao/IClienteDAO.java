package com.fijsac.proyecto.controller.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fijsac.proyecto.controller.model.entidad.Cliente;

public interface IClienteDAO  extends CrudRepository<Cliente,Long>{
    public List<Cliente> findAllByOrderByDn();
    @Procedure(name = "regist_op_hist")
    Void regist_op_hist(
        @Param("operacion") String operacion,
        @Param("codigo") String codigo
    );

    @Query(value = "SELECT id_cli, nombre, apellido, dni, celular FROM cliente ORDER BY id_cli DESC LIMIT 1", nativeQuery = true)
    public Cliente obtenerUltimoCliente();
}
