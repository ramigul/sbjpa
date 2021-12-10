package com.amadeux.springboot.app.models.service;

import java.util.List;

import com.amadeux.springboot.app.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	public void save(Cliente cliente);
	public Cliente findCustomer(Long id);
	public void delete(Long id);

}
