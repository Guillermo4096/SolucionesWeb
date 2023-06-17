package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fijsac.proyecto.controller.model.dao.IClienteDAO;
import com.fijsac.proyecto.controller.model.entidad.Cliente;

@Service
public class ClienteServiveImp implements IClienteService{

    @Autowired
    private IClienteDAO clienteDAO;

    @Override
    public List<Cliente> mostrarCliente() {
        return (List<Cliente>)clienteDAO.findAll();
    }

    @Override
    public List<Cliente> mostrarClienteOrdenado() {
        return clienteDAO.findAllByOrderByDn();
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteDAO.save(cliente);
    }

    @Override
    public Cliente buscarCliente(Long id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteDAO.deleteById(id);
    }
    
}
