package com.fijsac.proyecto.controller.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fijsac.proyecto.controller.model.entidad.Proveedor;

public interface IProveedorDAO extends CrudRepository<Proveedor,Long> {
    public List<Proveedor> findAllByOrderByNombre();
}
