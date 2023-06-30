package com.fijsac.proyecto.controller.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fijsac.proyecto.controller.model.entidad.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario,Long> {
    public List<Usuario> findAllByOrderByNombre();
    @Procedure(name = "regist_nuev_user")
    Void regist_nuev_user(
        @Param("o") int o
    );
    @Procedure(name = "regist_op_hist")
    Void regist_op_hist(
        @Param("operacion") String operacion,
        @Param("codigo") String codigo
    );
}
