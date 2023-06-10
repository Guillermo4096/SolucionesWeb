package com.fijsac.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @RequestMapping("/")
    public String ventas(){
        return "/html/ventas";
    }

    @RequestMapping("/nuevaVenta")
    public String nuevaVenta(){
        return "/html/nuevaVenta";
    }

    @RequestMapping("/registrarNuevaVenta")
    public String registrarNuevaVenta(){
        return "/html/registrarNuevaVenta";
    }
}
