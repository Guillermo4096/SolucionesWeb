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

    @Override
    public Proveedor buscarProveedor(Long id){
        return proveedorDAO.findById(id).orElse(null);
    }

    @Override
    public void guardarProveedor(Proveedor proveedor){
        proveedorDAO.save(proveedor);
        
        proveedorDAO.regist_op_hist(
            "Proveedor registrado o editado",
            "Ninguno"
        );
        
    }

    @Override
    public void eliminarProveedor(Long id, String ruc){
        proveedorDAO.deleteById(id);
        proveedorDAO.regist_op_hist(
            "Proveedor eliminado",
            ruc
        );
    }
}
