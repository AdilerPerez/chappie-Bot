package br.com.fiap.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TB_EXECUCAO")
public class ExecucaoModel {
	private long id;
	private Date data;
	private AcaoModel acao;
	
	public ExecucaoModel() {
		
	}
	
	public ExecucaoModel(int id, Date data) {
		this.id = id;
		this.data = data;
	}
	
	public ExecucaoModel(long id, Date data, AcaoModel acao) {
		this.id = id;
		this.data = data;
		this.acao = acao;
	}
	
	@Id
	@Column(name = "ID_EXECUCAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXECUCAO_SEQ")
	@SequenceGenerator(name = "EXECUCAO_SEQ", sequenceName = "EXECUCAO_SEQ", allocationSize = 1)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_ACAO", nullable = false)
	public AcaoModel getAcao() {
		return acao;
	}
	
	public void setAcao(AcaoModel acao) {
		this.acao = acao;
	}
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name="DATA")
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ExecucaoModel [id=" + id + ", data=" + data + ", acao=" + acao + "]";
	}
	
}
