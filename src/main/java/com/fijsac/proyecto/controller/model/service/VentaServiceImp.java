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
        ventaDAO.regist_nuev_vent(0);
    }
    
    @Override
    public void editarVenta(Venta venta) {
        ventaDAO.save(venta);
        ventaDAO.editar_monto_venta(venta.getId());
        ventaDAO.regist_op_hist("Venta editada", venta.getCod());
    }

    @Override
    public void eliminarVenta(Long id, Long cod_us) {
        ventaDAO.deleteById(id);
        ventaDAO.regist_op_hist("Venta eliminada", "Ninguno");
    }

    @Override
    public Venta buscarVenta(Long id) {
        return ventaDAO.findById(id).orElse(null);
    }

}
