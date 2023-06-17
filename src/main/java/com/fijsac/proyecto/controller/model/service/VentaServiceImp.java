package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fijsac.proyecto.controller.model.dao.IVentaDAO;
import com.fijsac.proyecto.controller.model.entidad.Venta;

@Service
public class VentaServiceImp implements IVentaService {

    @Autowired
    private  IVentaDAO ventaDAO;

    @Override
    public List<Venta> mostrarVenta() {
        return (List<Venta>)ventaDAO.findAll();
    }

    @Override
    public void guardarVenta(Venta venta) {
        ventaDAO.save(venta);
        ventaDAO.ingresar_monto_venta(0);
    }

    @Override
    public void eliminarVenta(Long id) {
        ventaDAO.deleteById(id);
    }

    @Override
    public Venta buscarVenta(Long id) {
        return ventaDAO.findById(id).orElse(null);
    }
    
}
