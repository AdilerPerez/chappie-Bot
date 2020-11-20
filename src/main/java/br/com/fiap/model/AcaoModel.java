package br.com.fiap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_ACAO")
public class AcaoModel {
	private long id;
	private String nome;
	private String descricao;
	private boolean ativo;
	private List<ExecucaoModel> execucao;
	
	
	public AcaoModel() {
		
	}


	public AcaoModel(long id, String nome, String descricao, Boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
	}
	
	@Id
	@Column(name = "ID_ACAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAO_SEQ")
	@SequenceGenerator(name = "ACAO_SEQ", sequenceName = "ACAO_SEQ", allocationSize = 1)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "NOME")
	@NotNull(message = "Nome obrigatorio")
	@Size(min = 2, max = 40, message = "NOME deve ser entre 2 e 40 caracteres")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "DESCRICAO")
	@NotNull(message = "Descrição obrigatória")
	@Size(min = 4, max = 100, message = "A descrição deve conter até 100 caracteres")
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@Column(name = "ATIVO")
	@NotNull(message = "Ativo Obrigatório")
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy="acao")
	public List<ExecucaoModel> getExecucao() {
		return execucao;
	}

	public void setExecucao(List<ExecucaoModel> execucao) {
		this.execucao = execucao;
	}
	
	@Override
	public String toString() {
		return "AcaoModel [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", ativo=" + ativo
				+ ", execucao=" + execucao + "]";
	}

	
}
