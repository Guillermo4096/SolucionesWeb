package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fijsac.proyecto.controller.model.entidad.Producto;
import com.fijsac.proyecto.controller.model.service.IProductoService;
import com.fijsac.proyecto.controller.model.service.IProveedorService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private IProductoService productoService;
    @Autowired
    private IProveedorService proveedorService;

    @RequestMapping("/")
    public String inicio(Model model){
        model.addAttribute("listaProducto", productoService.mostrarProducto());
        return "/html/producto";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(Producto producto){
        productoService.guardarProducto(producto);
        return "redirect:/producto/";
    }

    @RequestMapping(value = "/guardarEditar", method = RequestMethod.POST)
    public String guardarEditar(Producto producto){
        productoService.editarProducto(producto);
        return "redirect:/producto/";
    }

    @RequestMapping("/nuevoProducto")
    public String nuevoProducto(Model model){
        Producto producto = new Producto();
        model.addAttribute("producto", producto);

        model.addAttribute("listaProveedor", proveedorService.mostrarProveedorOrdenado());


        return "/html/nuevoProducto";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id")Long id){
        productoService.eliminarProducto(id);
        return "redirect:/producto/";
    }

    @RequestMapping("/editarProducto/{id}")
    public String editarProducto(@PathVariable(value = "id") Long id, Model model){
        Producto producto =new Producto();
        producto = productoService.buscarProducto(id);
        model.addAttribute("producto", producto);
        model.addAttribute("listaProveedor", proveedorService.mostrarProveedorOrdenado());
        model.addAttribute("listaProducto", productoService.mostrarProducto());
        return "/html/editarProducto";
    }
}
