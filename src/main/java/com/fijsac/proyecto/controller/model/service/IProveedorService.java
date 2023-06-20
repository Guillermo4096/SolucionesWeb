package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import com.fijsac.proyecto.controller.model.entidad.Proveedor;

public interface IProveedorService {
    public List<Proveedor> mostrarProveedor();
    public List<Proveedor> mostrarProveedorOrdenado();
    public Proveedor buscarProveedor(Long id);
    public void guardarProveedor (Proveedor proveedor);
    public void eliminarProveedor(Long id);
}
