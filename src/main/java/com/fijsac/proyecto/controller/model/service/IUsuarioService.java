package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import com.fijsac.proyecto.controller.model.entidad.Usuario;

public interface IUsuarioService {
    public void guardarUsuario(Usuario usuario);
    public List<Usuario> mostrarUsuario();
    public void eliminarUsuario(Long id);
    public Usuario buscarUsuario(Long id);

    public List<Usuario> mostrarUsuarioOrdenado();

}
