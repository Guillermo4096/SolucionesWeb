package com.fijsac.proyecto.controller.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijsac.proyecto.controller.model.dao.IUsuarioDAO;
import com.fijsac.proyecto.controller.model.entidad.Rol;
import com.fijsac.proyecto.controller.model.entidad.Usuario;

@Service("UserService")
public class UserService implements UserDetailsService{
    
    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           Usuario usuario = usuarioDAO.findByIdusuario(username);
        List<GrantedAuthority> listaRoles = new ArrayList<>();

        if(usuario == null){
            throw new UsernameNotFoundException("usuario/contraseña incorrecta");
        }else{
            for(Rol item : usuario.getRoles()){
                listaRoles.add(new SimpleGrantedAuthority(item.getAuthority()));
                
            }
        }
        return new User(usuario.getIdusuario(),usuario.getContra(),listaRoles);
   
    }
}
