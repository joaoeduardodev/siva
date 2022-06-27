package br.com.siva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.siva.repositories.VagaRepositorio;

@Controller
public class HomeController {
	

	@Autowired
	private VagaRepositorio vagaRepo;
	
	@GetMapping("/")
	public ModelAndView home() {		
		ModelAndView mv = new ModelAndView("index.html");
		mv.addObject("mensagem", "Futuro DASHBOARD");
		mv.addObject("vagas", vagaRepo.findAll());
		
		return mv;
	}	
}
