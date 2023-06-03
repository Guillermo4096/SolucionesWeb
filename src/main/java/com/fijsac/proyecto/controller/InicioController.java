package com.fijsac.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicioController {
    @RequestMapping("/")
    public String inicio(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "/html/ventas";
    }
    @RequestMapping("/devoluciones")
    public String devoluciones(){
        return "/html/gestion";
    }
    @RequestMapping("/registro")
    public String registro(){
        return "/html/registro";
    }
    @RequestMapping("/edicion")
    public String edit(){
        return "/html/edit";
    }

}
