package br.com.jawebsites.cadastro.produto;

public record DadosAlteracaoProduto(Long id, String codigo,String nome,
		Double preco,String medida,String obs) {}
