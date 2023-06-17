package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import com.fijsac.proyecto.controller.model.entidad.Venta;

public interface IVentaService {
    public List<Venta> mostrarVenta();
    public void guardarVenta(Venta venta);
    public void eliminarVenta(Long id);
    public Venta buscarVenta(Long id);
}
