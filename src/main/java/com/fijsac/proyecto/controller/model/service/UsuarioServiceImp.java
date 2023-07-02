package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fijsac.proyecto.controller.model.dao.IUsuarioDAO;
import com.fijsac.proyecto.controller.model.entidad.Usuario;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private IUsuarioDAO usuarioDAO;

      @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void guardarUsuario(Usuario usuario) {
        // Codificar la contraseña
        String contraseñaCodificada = passwordEncoder.encode(usuario.getContra());
        // Establecer la contraseña codificada en el objeto Usuario
        usuario.setContra(contraseñaCodificada);

        usuarioDAO.save(usuario);
        usuarioDAO.regist_nuev_user(0);
    }

    @Override
    public List<Usuario> mostrarUsuario() {
        return (List<Usuario>)usuarioDAO.findAll();
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioDAO.deleteById(id);
        usuarioDAO.regist_op_hist(
            "Usuario Eliminado", 
            "Ninguno"
        );
    }

    @Override
    public Usuario buscarUsuario(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> mostrarUsuarioOrdenado() {
        return usuarioDAO.findAllByOrderByNombre();
    }

    @Override
    public void editarUsuario(Usuario usuario) {
        // Codificar la contraseña
        String contraseñaCodificada = passwordEncoder.encode(usuario.getContra());
        // Establecer la contraseña codificada en el objeto Usuario
        usuario.setContra(contraseñaCodificada);

        usuarioDAO.save(usuario);
        usuarioDAO.regist_op_hist(
            "Usuario editado", 
            usuario.getIdusuario()
        );
    }
}
