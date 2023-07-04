package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fijsac.proyecto.controller.model.entidad.Venta;
import com.fijsac.proyecto.controller.model.service.IClienteService;
import com.fijsac.proyecto.controller.model.service.IProductoService;
import com.fijsac.proyecto.controller.model.service.IUsuarioService;
import com.fijsac.proyecto.controller.model.service.IVentaService;


@Controller
@RequestMapping("/venta")
//@RestController
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private JavaMailSender mail;

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
    public String guardar(Venta venta, RedirectAttributes flash){
        String rpta = ventaService.guardarVenta(venta);
        flash.addFlashAttribute("mensaje", rpta);
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
    
    @RequestMapping("/eliminar/{id}/{cod_us}")
    public String eliminar(@PathVariable(value = "id")Long id, @PathVariable(value = "cod_us")Long cod_us){
        ventaService.eliminarVenta(id, cod_us);
        return "redirect:/venta/";
    }
    
    @RequestMapping(value = "/guardarEditar", method = RequestMethod.POST)
    public String guardarEditar(Venta venta){
        ventaService.editarVenta(venta);
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

    @RequestMapping("/cancelar/{id}/{cantidad}/{cod_prod}")
    public String CancelarVenta(
        @PathVariable(value = "id")Long id, 
        @PathVariable(value = "cantidad")Long cantidad,
        @PathVariable(value = "cod_prod")Long cod_prod
        ){
        ventaService.cancelarVenta(id, cantidad, cod_prod);
        return "redirect:/venta/";
    }

    @RequestMapping(value = "/boleta/{id}")
    public String generarBoleta(@PathVariable(value = "id") Long id, Model model) {
        Venta venta = ventaService.detalleVenta(id);
        model.addAttribute("fechaBoleta", venta.getFech());
        model.addAttribute("descBoleta", venta.getDes());
        model.addAttribute("cantBoleta", venta.getCan());
        model.addAttribute("montoBoleta", venta.getMon());
        return "/html/boleta";
    }
}
