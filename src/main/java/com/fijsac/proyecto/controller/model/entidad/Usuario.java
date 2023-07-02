package com.fijsac.proyecto.controller.model.entidad;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod")
    private Long id;
    @Column(name="cod_us")
    private String idusuario;
    @Column(name="dni")
    private String dni;
    @Column(name="nom_ap")
    private String nombre;
    @Column(name="correo")
    private String correo;
    @Column(name="contraseña")
    private String contra;
    
    
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Rol> roles;

    

    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public List<Rol> getRoles() {
        return roles;
    }
    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContra() {
        return contra;
    }
    public void setContra(String contra) {
        this.contra = contra;
    }
    

    
}
