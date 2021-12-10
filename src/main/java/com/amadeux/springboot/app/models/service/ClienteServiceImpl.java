package com.amadeux.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amadeux.springboot.app.models.dao.IClienteDao;
import com.amadeux.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)//marcamos el método como transaccional y de lectura (consulta)
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);	
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findCustomer(Long id) {
		return clienteDao.findById(id).orElse(null);//Si lo encuentra retorna el id, de lo contrario retorna null.
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
		
	}

	
}
