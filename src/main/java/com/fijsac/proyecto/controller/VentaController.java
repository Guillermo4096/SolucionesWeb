package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fijsac.proyecto.controller.model.entidad.Venta;
import com.fijsac.proyecto.controller.model.service.IClienteService;
import com.fijsac.proyecto.controller.model.service.IProductoService;
import com.fijsac.proyecto.controller.model.service.IUsuarioService;
import com.fijsac.proyecto.controller.model.service.IVentaService;


@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IClienteService clienteService;

    @RequestMapping("/")
    public String venta(Model model){
        Venta venta = new Venta();
        model.addAttribute("venta", venta);

        model.addAttribute("listaVenta", ventaService.mostrarVenta());

        model.addAttribute("listaProducto", productoService.mostrarProductoOrdenado());
        model.addAttribute("listaUsuario", usuarioService.mostrarUsuarioOrdenado());
        model.addAttribute("listaCliente", clienteService.mostrarClienteOrdenado());

        return "/html/venta";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(Venta venta){
        ventaService.guardarVenta(venta);
        return "redirect:/venta/";
    }

  
    @RequestMapping("/nuevoVenta")
    public String nuevoVenta(Model model){
        Venta venta = new Venta();
        model.addAttribute("venta", venta);

        model.addAttribute("listaProducto", productoService.mostrarProductoOrdenado());
        model.addAttribute("listaUsuario", usuarioService.mostrarUsuarioOrdenado());
        model.addAttribute("listaCliente", clienteService.mostrarClienteOrdenado());

        return "/html/nuevoVenta";
    }
    
    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id")Long id){
        ventaService.eliminarVenta(id);
        return "redirect:/venta/";
    }

    @RequestMapping(value = "/guardarEditar/{id}", method = RequestMethod.POST)
    public String guardarEditar(Venta venta){
        ventaService.guardarVenta(venta);
        return "redirect:/venta/";
    }
    

    @RequestMapping("/editarVenta/{id}")
    public String editarVenta(@PathVariable(value = "id") Long id, Model model){
        Venta venta =new Venta();
        venta = ventaService.buscarVenta(id);

        model.addAttribute("venta", venta);
        model.addAttribute("listaVenta", ventaService.mostrarVenta());


        model.addAttribute("listaProducto", productoService.mostrarProductoOrdenado());
        model.addAttribute("listaUsuario", usuarioService.mostrarUsuarioOrdenado());
        model.addAttribute("listaCliente", clienteService.mostrarClienteOrdenado());


        return "/html/editarVenta";
    }

     @RequestMapping("/nuevoCliente")
    public String nuevoCliente(Model model){
        Venta venta = new Venta();
        model.addAttribute("venta", venta);

        model.addAttribute("listaProducto", productoService.mostrarProductoOrdenado());
        model.addAttribute("listaUsuario", usuarioService.mostrarUsuarioOrdenado());
        model.addAttribute("listaCliente", clienteService.mostrarClienteOrdenado());

        return "/html/nuevoCliente";
    }
    
    /*
    @RequestMapping("/nuevaVenta")
    public String nuevaVenta(){
        return "/html/nuevaVenta";
    }

    @RequestMapping("/registrarNuevaVenta")
    public String registrarNuevaVenta(){
        return "/html/registrarNuevaVenta";
    }
    */
}
