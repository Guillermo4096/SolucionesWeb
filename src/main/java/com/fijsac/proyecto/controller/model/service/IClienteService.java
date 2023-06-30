package com.fijsac.proyecto.controller.model.service;

import java.util.List;

import com.fijsac.proyecto.controller.model.entidad.Cliente;

public interface IClienteService {
    public List<Cliente> mostrarCliente();
    public void guardarCliente(Cliente cliente);
    public Cliente buscarCliente(Long id);
    public void eliminarCliente(Long id, String dni);
    public List<Cliente> mostrarClienteOrdenado();
}
