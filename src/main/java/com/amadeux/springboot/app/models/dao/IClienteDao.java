package com.amadeux.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.amadeux.springboot.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{//Long es el tipo del id cliente
	
	
	
}
