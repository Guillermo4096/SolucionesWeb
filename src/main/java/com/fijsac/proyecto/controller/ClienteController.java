package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fijsac.proyecto.controller.model.entidad.Cliente;
import com.fijsac.proyecto.controller.model.service.IClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private IClienteService clienteService;

    @RequestMapping("/")
    public String inicio(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("cliente",cliente);
        model.addAttribute("titulo", "Nuevo Cliente");
        model.addAttribute("btnFormC", "Registrar");
        model.addAttribute("listaCliente", clienteService.mostrarCliente());
        return "html/cliente";
    }

    @RequestMapping("/guardar")
    public String guardar(Cliente cliente){
        clienteService.guardarCliente(cliente);
        return "redirect:/cliente/";
    }
    @RequestMapping("/EditarCliente/{id}")
    public String editar(@PathVariable(value="id") Long id, Model model){
        Cliente cliente = new Cliente();
        cliente=clienteService.buscarCliente(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Modificar Cliente");
        model.addAttribute("btnFormC", "Modificar");
        model.addAttribute("listaCliente", clienteService.mostrarCliente());
        return "html/cliente";
    }
    @RequestMapping("/EliminarCliente/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){
        clienteService.eliminarCliente(id);
        return "redirect:/cliente/";
    }
}
