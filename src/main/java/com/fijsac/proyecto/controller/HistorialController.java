package com.fijsac.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fijsac.proyecto.controller.model.service.IHistoriaService;

@Controller
@RequestMapping("/historial")
public class HistorialController {
    @Autowired
    private IHistoriaService historialService;

    @RequestMapping("/")
    public String inicio(Model model){
        model.addAttribute("listaHistorial", historialService.mostrarHistorial());
        return "/html/historial";
    }
}
