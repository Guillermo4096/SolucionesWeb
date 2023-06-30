package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fijsac.proyecto.controller.model.dao.IProductoDAO;
import com.fijsac.proyecto.controller.model.entidad.Producto;

@Service
public class ProductoServiceImp implements IProductoService {

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    public void guardarProducto(Producto producto){
        productoDAO.save(producto);
        productoDAO.regist_nuev_prod(0);
    }

    @Override
    public List<Producto> mostrarProducto() {
        return (List<Producto>)productoDAO.findAll();
    }

    @Override
    public void eliminarProducto(Long id) {
        productoDAO.deleteById(id);
        productoDAO.regist_op_hist(
            "Producto Eliminado", 
            "Ninguno"
        );
    }

    @Override
    public Producto buscarProducto(Long id) {
        return productoDAO.findById(id).orElse(null);
    }

    @Override
    public List<Producto> mostrarProductoOrdenado() {
        return productoDAO.findAllByOrderByReferencia();
    }

    @Override
    public void editarProducto(Producto producto) {
        productoDAO.save(producto);
        productoDAO.regist_op_hist(
            "Producto editado", 
            producto.getReferencia()
        );
    }
}
