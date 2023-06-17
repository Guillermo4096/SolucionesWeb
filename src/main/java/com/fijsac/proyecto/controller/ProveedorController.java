package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fijsac.proyecto.controller.model.service.IProveedorService;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
    
    @Autowired
    private IProveedorService proveedorService;

    @RequestMapping("/")
    public String inicio(Model model){
        model.addAttribute("listaProveedor", proveedorService.mostrarProveedor());
         return "/html/proveedor";
    }

}
