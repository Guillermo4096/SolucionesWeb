package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fijsac.proyecto.controller.model.entidad.Proveedor;
import com.fijsac.proyecto.controller.model.service.IProveedorService;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
    
    @Autowired
    private IProveedorService proveedorService;

    @RequestMapping("/")
    public String inicio(Model model){
        Proveedor proveedor = new Proveedor();
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("titulo", "Nuevo Proveedor");
        model.addAttribute("btnFormC", "Registrar");
        model.addAttribute("listaProveedor", proveedorService.mostrarProveedor());
        return "html/proveedor";
    }

    @RequestMapping("/guardar")
    public String guardar(Proveedor proveedor){
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/proveedor/";
    }

    @RequestMapping("/EditarProveedor/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model){
        Proveedor proveedor = new Proveedor();
        proveedor = proveedorService.buscarProveedor(id);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("titulo", "Modificar Proveedor");
        model.addAttribute("btnFormC", "Modificar");
        model.addAttribute("listaProveedor", proveedorService.mostrarProveedor());
        return "html/proveedor";
    }

    @RequestMapping("/EliminarProveedor/{id}/{ruc}")
    public String eliminar(@PathVariable(value = "id") Long id, 
                            @PathVariable(value = "ruc") String ruc){
        proveedorService.eliminarProveedor(id, ruc);
        return "redirect:/proveedor/";
    }
}
