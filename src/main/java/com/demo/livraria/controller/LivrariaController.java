package com.demo.livraria.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.demo.livraria.model.Autor;
import com.demo.livraria.model.Livro;

@Controller
@RequestMapping("/api")
public class LivrariaController {

	private static RestTemplate restTemplate = new RestTemplate();
	private static Map<Integer, Autor> autores = new HashMap<Integer, Autor>();

	@GetMapping()
	public String inicio() {
		return "index";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		return "index";
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<Livro>> buscar() {

		// adiciona autor
		if (autores.isEmpty()) {
			Autor[] listaAutor = restTemplate.getForObject("https://bibliapp.herokuapp.com/api/authors", Autor[].class);
			for (int i = 0; i < listaAutor.length; i++) {
				autores.put(listaAutor[i].getId(), listaAutor[i]);
			}
		}

		List<Livro> listaLivros = Arrays.asList(restTemplate.getForObject("https://bibliapp.herokuapp.com/api/books", Livro[].class));
//		for (Livro liv : listaLivros) {
//			liv.setAutor(autores.get(liv.getAuthorId()));
//		}
		return ResponseEntity.ok(listaLivros);
	}

	@GetMapping("/cadastro")
	public String cadastro(Model model) {
		Livro livro = new Livro();
		model.addAttribute("livro", livro);
		return "cadastro";
	}

	@GetMapping("/edicao/{id}")
	public String edicao(Model model, @PathVariable("id") Integer id) {
		Livro livro = restTemplate.getForEntity("https://bibliapp.herokuapp.com/api/books/" + id, Livro.class)
				.getBody();
		model.addAttribute("livro", livro);
		return "edicao";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Model model, @ModelAttribute("livro") Livro livro) {
		try {
			HttpEntity<Livro> request = new HttpEntity<>(
					new Livro(null, livro.getTitle(), livro.getIsbn(), livro.getAuthorId()));
			restTemplate.postForObject("https://bibliapp.herokuapp.com/api/books", request, Livro.class);
		} catch (Exception ex) {
			String errorMessage = ex.getMessage();
			model.addAttribute("errorMessage", errorMessage);
		}
		return listar(model);
	}

	@PostMapping("/editar")
	public String editar(Model model, @ModelAttribute("livro") Livro livro) {
		try {
			HttpEntity<Livro> request = new HttpEntity<>(
					new Livro(livro.getId(), livro.getTitle(), livro.getIsbn(), livro.getAuthorId()));
			restTemplate.put("https://bibliapp.herokuapp.com/api/books", request);
			model.addAttribute("msg", "Livro editado.");
		} catch (Exception ex) {
			String errorMessage = ex.getMessage();
			model.addAttribute("errorMessage", errorMessage);
		}
		return listar(model);
	}

	@GetMapping("/deletar/{id}")
	public String deletar(Model model, @PathVariable("id") Integer id) {
		restTemplate.delete("https://bibliapp.herokuapp.com/api/books/" + id);
		model.addAttribute("msg", "Livro removido.");
		return listar(model);
	}

}
