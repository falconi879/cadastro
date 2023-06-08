package br.com.jawebsites.cadastro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jawebsites.cadastro.produto.DadosAlteracaoProduto;
import br.com.jawebsites.cadastro.produto.DadosCadastroProduto;
import br.com.jawebsites.cadastro.produto.Produto;
import br.com.jawebsites.cadastro.produto.ProdutoRepository;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@GetMapping("formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		if (id != null) {
			var produto = repository.getReferenceById(id);
			model.addAttribute("produto", produto);
		}
		return "produtos/formulario";
	}

	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", repository.findAll());
		return "produtos/listagem";
	}

	@PostMapping
	public String cadastraProduto(DadosCadastroProduto dados) {
		if (dados.nome() == "" || dados.codigo() == "") {
			return "produtos/formulario";
		}
		var produto = new Produto(dados);
		repository.save(produto);
		return "redirect:/produtos";
	}

	@PutMapping
	@Transactional
	public String alteraFilme(DadosAlteracaoProduto dados) {
		var produto = repository.getReferenceById(dados.id());
		produto.atualizaDados(dados);
		return "redirect:/produtos";
	}
	@DeleteMapping
    @Transactional
    public String removeProduto(Long id) {
        repository.deleteById(id);
        return "redirect:/produtos";
    }
}
