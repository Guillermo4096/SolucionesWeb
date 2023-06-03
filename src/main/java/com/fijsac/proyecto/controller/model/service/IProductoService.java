package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import com.fijsac.proyecto.controller.model.entidad.Producto;

public interface IProductoService {
    public void guardarProducto(Producto producto);
    public List<Producto> mostrarProducto();
    public void eliminarProducto(Long id);
    public Producto buscarProducto(Long id);
}
