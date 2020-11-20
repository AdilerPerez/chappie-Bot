package br.com.fiap.business;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.fiap.business.ExecucaoBusiness;
import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.AcaoRepository;
import br.com.fiap.repository.ExecucaoRepository;

public class ExecucaoBusinessTest {
	@InjectMocks
	public ExecucaoBusiness execucaoBusiness;
	
	@Mock
	public AcaoRepository acaoRepository;
	
	@Mock
	public ExecucaoRepository execucaoRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testReturnAcaoAtiva() {
		AcaoModel acaoModel = new AcaoModel(1, "teste", "teste", false);
		boolean expected = false;
		
		Mockito.when(acaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(acaoModel));
		
		boolean actual = execucaoBusiness.acaoAtiva(acaoModel);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testDateAtual() {
		ExecucaoModel execucaoModel = new ExecucaoModel(1, new Date());
		
		execucaoBusiness.dataAtual(execucaoModel);
	}
	
	@Test
	public void testApplyBusiness() throws ResponseBusinessException {
		AcaoModel acaoModel = new AcaoModel(1, "Correr", "Chappie inicia o modo fuga", true);
		ExecucaoModel execucao = new ExecucaoModel(1, new Date(), acaoModel);
		
		ExecucaoModel expected = new ExecucaoModel(1, new Date(), acaoModel);
		
		Mockito.when(acaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(acaoModel));
		
		ExecucaoModel actual = execucaoBusiness.applyBusiness(execucao);
		
		assertEquals(expected.toString(), actual.toString());
		
	}

}


