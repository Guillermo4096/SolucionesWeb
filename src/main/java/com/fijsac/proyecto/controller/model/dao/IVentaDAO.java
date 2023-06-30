package com.fijsac.proyecto.controller.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fijsac.proyecto.controller.model.entidad.Venta;

public interface IVentaDAO extends CrudRepository<Venta,Long> {
    @Procedure(name = "ingresar_monto_venta")
    Void ingresar_monto_venta(@Param("r") int r);

    @Procedure(name = "editar_monto_venta")
    Void editar_monto_venta(@Param("a") Long a);

    
    @Procedure(name = "regist_op_hist")
    Void regist_op_hist(
        @Param("operacion") String operacion,
        @Param("codigo") String codigo
    );
    @Procedure(name = "regist_nuev_vent")
    Void regist_nuev_vent(
        @Param("o") int o
    );
    
    @Query(value = "SELECT PRODUCTO.STOCK FROM PRODUCTO WHERE cod_prod=?1", nativeQuery = true)
    public int obtenerStockProducto(Long cod_prod);

    
    @Procedure(name = "act_stock_prod")
    Void act_stock_prod(
        @Param("stock") int stock,
        @Param("cod_prod") Long cod_prod
    );
}
