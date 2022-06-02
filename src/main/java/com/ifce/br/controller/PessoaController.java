package com.ifce.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifce.br.model.Pessoa;
import com.ifce.br.service.PessoaService;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/OlaMundo")
	public String OlaMundo() {
		
		return "ola-mundo";
		
	}
	
	@GetMapping("/pessoa/formulario")
	public String formulario() {
		
		return "formulario";
		
	}
	
	@GetMapping("/pessoa/salvar")
	public String salvar(Pessoa pessoa) {
		pessoaService.cadastrarPessoa(pessoa);
		
		return "sucesso";
		
	}
	
	@GetMapping("/pessoa/listar")
	public ModelAndView listarPessoas() {
		List<Pessoa> pessoas = pessoaService.listarPessoas();
		ModelAndView mv = new ModelAndView("listagem-pessoa");
		
		mv.addObject("listarPessoas", pessoas);
		
		return mv;
		
	}
	
	@RequestMapping("/pessoa/excluir/{codigo}")
	public ModelAndView excluirPessoaPorId (@PathVariable Long codigo) {
		pessoaService.excluirPessoaPorId(codigo);
		ModelAndView mv = new ModelAndView("redirect:/pessoa/listar");
		
		return mv;
	}

}
