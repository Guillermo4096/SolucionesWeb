package com.fijsac.proyecto.controller.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fijsac.proyecto.controller.model.dao.IHistorialDAO;
import com.fijsac.proyecto.controller.model.entidad.Historia;

@Service
public class HistoriaServiceImp implements IHistoriaService{

    @Autowired
    private IHistorialDAO historialDAO;

    @Override
    public List<Historia> mostrarHistorial() {
        return (List<Historia>)historialDAO.findAll();
    }
}
