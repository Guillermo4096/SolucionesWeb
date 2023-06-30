package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fijsac.proyecto.controller.model.entidad.Usuario;
import com.fijsac.proyecto.controller.model.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private IUsuarioService usuarioService;

    @RequestMapping("/")
    public String inicio(Model model){
        //Usuario usuario = new Usuario();
       // model.addAttribute("usuario", usuario);
        model.addAttribute("listaUsuario", usuarioService.mostrarUsuario());
        return "/html/usuario";
    }

    @RequestMapping("/guardar")
    public String guardar(Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuario/";
    }
    @RequestMapping("/guardarEditar")
    public String guardarEditar(Usuario usuario){
        usuarioService.editarUsuario(usuario);
        return "redirect:/usuario/";
    }

    @RequestMapping("/nuevoUsuario")
    public String nuevoUsuario(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "/html/nuevoUsuario";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id")Long id){
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuario/";
    }

    @RequestMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable(value = "id") Long id, Model model){
        Usuario usuario =new Usuario();
        usuario = usuarioService.buscarUsuario(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listaUsuario", usuarioService.mostrarUsuario());
        return "/html/editarUsuario";
    }
}
