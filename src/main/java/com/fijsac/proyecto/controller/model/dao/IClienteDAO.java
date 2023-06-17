package com.fijsac.proyecto.controller.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fijsac.proyecto.controller.model.entidad.Cliente;

public interface IClienteDAO  extends CrudRepository<Cliente,Long>{
    public List<Cliente> findAllByOrderByDn();
}
