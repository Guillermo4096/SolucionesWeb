package com.fijsac.proyecto.controller.model.entidad;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

    @Entity
    @Table(name="producto")
    public class Producto implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="cod_prod")
        private Long id;
        @Column(name="SKU")
        private String referencia;
        @Column(name="fecha")
        @Temporal(TemporalType.DATE)
        private Date fech;
        @Column(name="descripcion")
        private String desc;
        @Column(name="stock")
        private String tock;
        @Column(name="estado")
        private String est;
        @Column(name="precio")
        private String pre;
        
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getReferencia() {
            return referencia;
        }
        public void setReferencia(String referencia) {
            this.referencia = referencia;
        }
        public Date getFech() {
            return fech;
        }
        public void setFech(Date fech) {
            this.fech = fech;
        }
        public String getDesc() {
            return desc;
        }
        public void setDesc(String desc) {
            this.desc = desc;
        }
        public String getTock() {
            return tock;
        }
        public void setTock(String tock) {
            this.tock = tock;
        }
        public String getEst() {
            return est;
        }
        public void setEst(String est) {
            this.est = est;
        }
        public String getPre() {
            return pre;
        }
        public void setPre(String pre) {
            this.pre = pre;
        }

    }
