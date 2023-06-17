package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fijsac.proyecto.controller.model.dao.IProveedorDAO;
import com.fijsac.proyecto.controller.model.entidad.Proveedor;

@Service
public class ProveedorServiceImp implements IProveedorService{

    @Autowired
    private IProveedorDAO proveedorDAO;

    @Override
    public List<Proveedor> mostrarProveedor() {
        return (List<Proveedor>)proveedorDAO.findAll();

    }
    
    @Override
    public List<Proveedor> mostrarProveedorOrdenado() {
        return proveedorDAO.findAllByOrderByNombre();

    }
}
