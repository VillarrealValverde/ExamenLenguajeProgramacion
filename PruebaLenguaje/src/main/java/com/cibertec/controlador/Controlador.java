package com.cibertec.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.InterfaceService.IEmpleadoService;
import com.cibertec.modelo.Empleado;



@Controller
@RequestMapping
public class Controlador {
	
	@Autowired
	private IEmpleadoService service;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Empleado>empleados=service.listar();
		model.addAttribute("empleados",empleados);
		return "index";
	}

	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("empleado",new Empleado());
		return "form";
	}
	
	@PostMapping("/save")
	public String save(@Validated Empleado p, Model model) {
		service.save(p);
		return "redirect:/listar";
	}
	
	@GetMapping("/editar/{idEmpleado}")
	public String editar(@PathVariable int idEmpleado,  Model model) {
		Optional<Empleado>empleado=service.listarId(idEmpleado);
		model.addAttribute("empleado",empleado);
		return "form";
	}
	
	@GetMapping("/eliminar/{idEmpleado}")
	public String delete(@PathVariable int idEmpleado,  Model model) {
		service.delete(idEmpleado);
		return "redirect:/listar";
	}
}
