package br.com.jawebsites.cadastro.produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_produtos")
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nome;
	private Double preco;
	private String medida;
	private String obs;
	private Boolean status = false;
	
	public Produto(DadosCadastroProduto dados) {
		this.codigo=dados.codigo().toUpperCase();
		this.nome=dados.nome().toUpperCase();
		this.preco=dados.preco();
		this.medida=dados.medida().toUpperCase();
		this.obs=dados.obs();
		this.status=true;
	}

	public void atualizaDados(DadosAlteracaoProduto dados) {
		if(dados.codigo()!=null) {
			this.codigo=dados.codigo().toUpperCase();
		}
		
		if(dados.nome()!=null) {
			this.nome=dados.nome().toUpperCase();
		}
		if(dados.preco()!=null) {
			this.preco=dados.preco();
		}
		if(dados.medida()!=null) {
			this.medida=dados.medida();
		}
		if(dados.obs()!=null) {
			this.obs=dados.obs();
		}
	}
}
