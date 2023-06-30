package com.fijsac.proyecto.controller.model.dao;

import com.fijsac.proyecto.controller.model.entidad.Historia;
import org.springframework.data.repository.CrudRepository;

public interface IHistorialDAO extends CrudRepository<Historia,Long>{
    
}
