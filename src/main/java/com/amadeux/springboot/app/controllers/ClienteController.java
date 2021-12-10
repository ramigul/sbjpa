package com.amadeux.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.amadeux.springboot.app.models.entity.Cliente;
import com.amadeux.springboot.app.models.service.IClienteService;



@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	//@Qualifier("clienteDaoJPA")//Dicierne que clase de las que implemente IClienteDao vamos a utilizar. 
	//En este caso ClienteDaoImpl (@Repository("clienteDaoJPA"))
	private IClienteService clienteService;
	
	@RequestMapping(value={"/listar",""}, method=RequestMethod.GET) //Esto es igual: @GetMapping("/listar")
	public String formulario(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes_", clienteService.findAll());
		return "listar";
	}
	
	@GetMapping("/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		
		if(id > 0) {
			cliente= clienteService.findCustomer(id);
		}else {
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}
	
	@PostMapping("/form")//@SessionAttributes("cliente") -> SessionStatus status
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		
		//Si hay errores al guardar
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		clienteService.save(cliente);
		status.setComplete();//elimina el objeto cliente de la sesión
		return "redirect:listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable(value="id") Long id) {
		
		if(id > 0) {
			clienteService.delete(id);
		}
		return "redirect:/listar";
	}

}
