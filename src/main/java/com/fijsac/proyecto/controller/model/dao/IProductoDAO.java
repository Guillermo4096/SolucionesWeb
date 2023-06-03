package com.fijsac.proyecto.controller.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.fijsac.proyecto.controller.model.entidad.Producto;

public interface IProductoDAO extends CrudRepository<Producto,Long> {
    
}
