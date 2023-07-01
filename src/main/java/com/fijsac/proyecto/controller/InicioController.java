package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fijsac.proyecto.controller.model.entidad.Venta;
import com.fijsac.proyecto.controller.model.service.IVentaService;

@Controller
public class InicioController {
    @Autowired
    private IVentaService ventaService;

    @RequestMapping("/")
    public String inicio(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/registro")
    public String registro(){
        return "/html/registro";
    }
    @RequestMapping("/edicion")
    public String edit(){
        return "/html/edit";
    }    
    
    @RequestMapping("/dashboard")
    public String dashboard(Model model){
        int cantidad_ventas = ventaService.obtenerCantidadVentasHoy();
        model.addAttribute("ventasDeHoy", cantidad_ventas);
        return "/html/dashboard";
    }

}
