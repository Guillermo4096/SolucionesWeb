package com.fijsac.proyecto.controller.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fijsac.proyecto.controller.model.entidad.Producto;

public interface IProductoDAO extends CrudRepository<Producto,Long> {
    public List<Producto> findAllByOrderByReferencia();
    @Procedure(name = "regist_nuev_prod")
    Void regist_nuev_prod(
        @Param("o") int o
    );
    @Procedure(name = "regist_op_hist")
    Void regist_op_hist(
        @Param("operacion") String operacion,
        @Param("codigo") String codigo
    );
    @Query(value = "SELECT p.cod_prod, p.SKU, p.fecha, p.descripcion, p.stock, p.estado, p.precio, p.id_prov, SUM(v.cantidad) AS total_vendido " +
            "FROM producto p " +
            "JOIN venta v ON p.cod_prod = v.cod_prod " +
            "GROUP BY p.cod_prod " +
            "ORDER BY total_vendido DESC " +
            "LIMIT 1", nativeQuery = true)
    public Producto obtenerProductoMasVendido();
}
