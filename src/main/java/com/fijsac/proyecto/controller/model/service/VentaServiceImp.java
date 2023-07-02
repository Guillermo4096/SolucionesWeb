package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fijsac.proyecto.controller.model.dao.IVentaDAO;
import com.fijsac.proyecto.controller.model.entidad.Venta;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class VentaServiceImp implements IVentaService {

    @Autowired
    private  IVentaDAO ventaDAO;

    @Override
    public List<Venta> mostrarVenta() {
        return (List<Venta>)ventaDAO.findAll();
    }

    @Override
    public String guardarVenta(Venta venta) {
        int prodStock = ventaDAO.obtenerStockProducto(venta.getProducto().getId());
        int solStock = Integer.parseInt(venta.getCan());
        String rpta ="";
        try {
            if((prodStock-solStock)>=0){
                ventaDAO.save(venta);
                ventaDAO.ingresar_monto_venta(0);
                ventaDAO.regist_nuev_vent(0);
                ventaDAO.act_stock_prod(prodStock-solStock, venta.getProducto().getId());
                rpta="paso";
            }else{
                rpta="El stock disponible es insuficiente o el producto no esta disponible";
            }    
        } catch (Exception e) {
            rpta=e.getMessage();
        }
        
        return rpta;
    }
    
    @Override
    public void editarVenta(Venta venta) {
        ventaDAO.save(venta);
        ventaDAO.editar_monto_venta(venta.getId());
        ventaDAO.regist_op_hist("Venta editada", venta.getCod());
    }

    @Override
    public void eliminarVenta(Long id, Long cod_us) {
        ventaDAO.deleteById(id);
        ventaDAO.regist_op_hist("Venta eliminada", "Ninguno");
    }

    @Override
    public Venta buscarVenta(Long id) {
        return ventaDAO.findById(id).orElse(null);
    }

    @Override
    public int obtenerCantidadVentasHoy(){
        return ventaDAO.obtenerCantidadVentasHoy();
    }

    @Override
    public Venta obtenerUltimaVenta(){
        return ventaDAO.obtenerUltimaVenta();
    }
    
    @Override
    public String cancelarVenta(Long id, Long cantidad, Long idprod) {
        int prodStock = ventaDAO.obtenerStockProducto(idprod);
        int newCantidad = Integer.parseInt(""+cantidad);
        int newProdStock = prodStock + newCantidad;
        ventaDAO.act_stock_prod(newProdStock, idprod);
        ventaDAO.deleteById(id);
        return "";
    }

    /*private JavaMailSender mailSender;

    public VentaServiceImp(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void send(String from, String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage(null);
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendWithAttach(String from, String to, String subjec,
                                String text, String attachName,
                                InputStreamSource inputStream) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subjec);
        helper.setText(text, true);
        helper.addAttachment(attachName, inputStream);
        mailSender.send(message);
    }*/
}
