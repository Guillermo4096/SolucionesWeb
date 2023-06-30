package com.fijsac.proyecto.controller.model.entidad;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "historial")
public class Historia implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod")
    private Long cod;
    
    @Column(name="cod_op")
    private String cod_op;

    @Column(name="fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name="operacion")
    private String operacion;

    @Column(name="codigo")
    private String codigo;

    @PrePersist
    @PreUpdate
    public void asignarFecha(){
        fecha=new Date();
    }

    public Long getCod() {return cod;}
    public void setCod(Long cod) {this.cod = cod;}

    public String getCod_op() {return cod_op;}
    public void setCod_op(String cod_op) {this.cod_op = cod_op;}

    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}

    public String getOperacion() {return operacion;}
    public void setOperacion(String operacion) {this.operacion = operacion;}

    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}
}
