package com.fijsac.proyecto.controller.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.fijsac.proyecto.controller.model.entidad.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario,Long> {
    
}
