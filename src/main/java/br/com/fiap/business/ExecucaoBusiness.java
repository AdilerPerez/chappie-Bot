package br.com.fiap.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.AcaoRepository;

@Component
public class ExecucaoBusiness {

	@Value("${rest.exception.business.containsHttps}")
	private String containsHttps;
	
	@Autowired
	AcaoRepository acaoRepository;

	public ExecucaoModel applyBusiness(ExecucaoModel execucao) throws ResponseBusinessException {
			
		AcaoModel ativo = execucao.getAcao();
		
		if(acaoAtiva(ativo) == false) 
			throw new ResponseBusinessException("só é possível cadastrar execuções para ações ativas!");
	
		dataAtual(execucao);
		
		return execucao;
	
	}
	
	public boolean acaoAtiva(AcaoModel acao) {
		AcaoModel acaoModel = acaoRepository.findById(acao.getId()).get();
		return acaoModel.getAtivo();
	}
	
	public void dataAtual(ExecucaoModel execucao) {
		execucao.setData(new Date());
	}
}
