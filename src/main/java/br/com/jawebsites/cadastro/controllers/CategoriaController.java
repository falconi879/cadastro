package br.com.jawebsites.cadastro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jawebsites.cadastro.categoria.CadastroCategoria;
import br.com.jawebsites.cadastro.categoria.Categoria;
import br.com.jawebsites.cadastro.categoria.CategoriaRepository;
import br.com.jawebsites.cadastro.categoria.DadosCategoria;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;

	@GetMapping("formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		if (id != null) {
			var categoria = repository.getReferenceById(id);
			model.addAttribute("categoria", categoria);
		}
		return "categorias/formulario";
	}
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", repository.findAll());
		return "categorias/listagem";
	}
	@PostMapping
	public String cadastraCategoria(CadastroCategoria dados) {
		if (dados.nome() == "" || dados.codigo() == "") {
			return "produtos/formulario";
		}
		var categoria = new Categoria(dados);
		repository.save(categoria);
		return "redirect:/categorias";
	}
	@PutMapping
	@Transactional
	public String alteraCadastro(DadosCategoria dados) {
		var produto = repository.getReferenceById(dados.id());
		produto.atualizaDados(dados);
		return "redirect:/categorias";
	}
	@DeleteMapping
    @Transactional
    public String removeProduto(Long id) {
        repository.deleteById(id);
        return "redirect:/categorias";
    }
}
