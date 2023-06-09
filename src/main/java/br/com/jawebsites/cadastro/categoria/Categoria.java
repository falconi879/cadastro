package br.com.jawebsites.cadastro.categoria;

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
@Table(name = "tb_categorias")
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nome;
	private Boolean status = false;
	
	public Categoria(CadastroCategoria dados) {
		this.codigo = dados.codigo();
		this.nome = dados.nome();
		this.status=true;
	}

	public void atualizaDados(DadosCategoria dados) {
		if(dados.codigo()!=null) {
			this.codigo=dados.codigo();
		}
		if(dados.nome()!=null) {
			this.nome=dados.nome();
		}
		
	}
	
}
