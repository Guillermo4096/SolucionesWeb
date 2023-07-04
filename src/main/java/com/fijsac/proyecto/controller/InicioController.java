package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fijsac.proyecto.controller.model.entidad.Cliente;
import com.fijsac.proyecto.controller.model.entidad.Producto;
import com.fijsac.proyecto.controller.model.entidad.Venta;
import com.fijsac.proyecto.controller.model.service.IClienteService;
import com.fijsac.proyecto.controller.model.service.IProductoService;
import com.fijsac.proyecto.controller.model.service.IVentaService;

@Controller
public class InicioController {
    @Autowired
    private IVentaService ventaService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProductoService productoService;

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
    
    @RequestMapping("/dashboard/")
    public String dashboard(Model model){
        int cantidad_ventas = ventaService.obtenerCantidadVentasHoy();
        model.addAttribute("ventasDeHoy", cantidad_ventas);

        //Último Venta
        Venta ultimaVenta = ventaService.obtenerUltimaVenta();
        if (ultimaVenta != null) {
            model.addAttribute("codVenta", ultimaVenta.getCod());
            model.addAttribute("fechaVenta", ultimaVenta.getFech());
            model.addAttribute("montoVenta", "S/. "+ ultimaVenta.getMon());
        } else {
            model.addAttribute("codVenta", "-");
            model.addAttribute("fechaVenta", "-");
            model.addAttribute("montoVenta", "-");
        }
        //Producto más vendido
        Producto prodMasVendido = productoService.obtenerProductoMasVendido();
        if(prodMasVendido != null){
            model.addAttribute("codProd", prodMasVendido.getReferencia());
            model.addAttribute("desProd", prodMasVendido.getDesc());
            model.addAttribute("preProd", "S/. " + prodMasVendido.getPre());
        } else{
            model.addAttribute("codProd", "-");
            model.addAttribute("desProd", "-");
            model.addAttribute("preProd", "-");
        }

        //Último Cliente
        Cliente ultimoCliente = clienteService.obtenerUltimoCliente();
        model.addAttribute("idCliente", ultimoCliente.getId());
        model.addAttribute("nCliente", ultimoCliente.getNom());
        model.addAttribute("aCliente", ultimoCliente.getApel());
        model.addAttribute("dCliente", ultimoCliente.getDn());
        model.addAttribute("cCliente", ultimoCliente.getCel());

        return "/html/dashboard";
    }

}
