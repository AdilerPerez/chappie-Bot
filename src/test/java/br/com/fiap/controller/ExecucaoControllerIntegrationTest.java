package br.com.fiap.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ExecucaoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	@DisplayName("Deve listar todas as execucoes e retornar status 200")
	public void shouldListAllExecucoes() throws Exception {
		
		mvc.perform(get("/execucao")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Deve retornar uma execucao pelo ID e com status 200")
	public void shouldFindExecucaoById() throws Exception {
		
		mvc.perform(get("/execucao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"id\":1,\"data\":\"10-11-2020 18:30:20\",\"acao\":{\"id\":1,\"nome\":\"CORRER\",\"descricao\":\"CHAPPIE COMECA A CORRER\",\"ativo\":false}}"));
	}
	
	@Test
	@DisplayName("Deve salvar uma execucao, retornar status 201 e Location no Header")
	public void shouldSaveExecucao() throws Exception {
		
		mvc.perform(post("/execucao")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"acao\":{\"id\":\"3\"}}"))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"));
	}
	

}
