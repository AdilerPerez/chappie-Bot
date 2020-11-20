package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.repository.AcaoRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/acao")
public class AcaoController {
	@Autowired
	public AcaoRepository repository;
	
	
	@GetMapping()
	@ApiOperation(value = "Retorna uma lista de acoes")
	public ResponseEntity<List<AcaoModel>> findAll() {

		List<AcaoModel> acoes = repository.findAll();
		return ResponseEntity.ok(acoes);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna uma execucao atraves do id")
	public ResponseEntity<AcaoModel> findById(@PathVariable("id") long id) {
		
		AcaoModel acao = repository.findById(id).get();
		return ResponseEntity.ok(acao);
	}
	
	@PostMapping()
	@ApiOperation(value = "Salva uma acao")
	public ResponseEntity save(@RequestBody @Valid AcaoModel acaoModel) throws ResponseBusinessException {
		
		AcaoModel acao = repository.save(acaoModel);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(acao.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Edita uma acao")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody @Valid AcaoModel acaoModel) throws ResponseBusinessException {

		acaoModel.setId(id);
		repository.save(acaoModel);

		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta uma acao")
	public ResponseEntity deleteById(@PathVariable("id") long id) {
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
