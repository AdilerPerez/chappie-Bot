package br.com.fiap.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class AcaoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	@DisplayName("Deve listar todas acoes e retornar status 200")
	public void shouldListAllExecucoes() throws Exception {
		
		mvc.perform(get("/acao")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Deve retornar uma acao pelo ID e com status 200")
	public void shouldFindExecucaoById() throws Exception {
		
		mvc.perform(get("/acao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"id\":1,\"nome\":\"CORRER\",\"descricao\":\"CHAPPIE COMECA A CORRER\",\"ativo\":false}"));
		
	
	}
	
	@Test
	@DisplayName("Deve salvar uma acao, retornar status 201 e Location no Header")
	public void shouldSaveAcao() throws Exception {
		
		mvc.perform(post("/acao")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"nome\":\"CORRER\",\"descricao\":\"CHAPPIE COMECA A CORRER\",\"ativo\":\"false\"}"))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"));
		
		
	}
	
	@Test
	@DisplayName("Deve atualizar uma acao pelo id e retornar status 200")
	public void shouldUpdateCategory() throws Exception {
		
		mvc.perform(put("/acao/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"DESTRUIR\",\"descricao\":\"DESTRUIÇÃO\",\"ativo\":\"true\"}"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Deve deletar uma acao pelo id e retornar status 204")
	public void shouldDeleteAcaoById() throws Exception {
		
		mvc.perform(delete("/acao/3")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

}
