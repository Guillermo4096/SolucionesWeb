package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import com.fijsac.proyecto.controller.model.entidad.Venta;

public interface IVentaService {
    public List<Venta> mostrarVenta();
    public String guardarVenta(Venta venta);
    public void editarVenta(Venta venta);
    public void eliminarVenta(Long id, Long cod_us);
    public Venta buscarVenta(Long id);
}
