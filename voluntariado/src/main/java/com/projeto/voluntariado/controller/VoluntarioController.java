package com.projeto.voluntariado.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.voluntariado.entity.Voluntario;
import com.projeto.voluntariado.service.VoluntarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Voluntario", description = "API REST DE GERENCIAMENTO DE VOLUNTARIO")
@RestController
@RequestMapping("/voluntario/")
public class VoluntarioController {

	private final VoluntarioService voluntarioService;

	@Autowired
	public VoluntarioController(VoluntarioService voluntarioService) {
		this.voluntarioService = voluntarioService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza um voluntario por ID")
	public ResponseEntity<Voluntario> buscaVoluntarioControlId(@PathVariable Long id) {
		Voluntario voluntario = voluntarioService.buscaVoluntarioId(id);
		if (voluntario != null) {
			return ResponseEntity.ok(voluntario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta os voluntarios")
	public ResponseEntity<List<Voluntario>> buscaTodosVoluntarioControl() {
		List<Voluntario> Voluntario = voluntarioService.buscaTodosVoluntario();
		return ResponseEntity.ok(Voluntario);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um voluntario")
	public ResponseEntity<Voluntario> salvaVoluntarioControl(@RequestBody @Valid Voluntario voluntario) {
		Voluntario salvaVoluntario = voluntarioService.salvaVoluntario(voluntario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaVoluntario);
	}
	
	@PostMapping("/check")
    public ResponseEntity<String> checkVoluntario(@RequestBody Map<String,String> payload) {
        String email = payload.get("email");
         if (voluntarioService.isVoluntarioRegistered(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado.");
        } else {
            return ResponseEntity.ok("Email disponível para cadastro.");
        }
    }

	@PutMapping("/{id}")
	@Operation(summary = "Altera um voluntario")
	public ResponseEntity<Voluntario> alterarVoluntarioControl(@PathVariable Long id,
			@RequestBody @Valid Voluntario voluntario) {
		Voluntario alterarVoluntario = voluntarioService.alterarVoluntario(id, voluntario);
		if (alterarVoluntario != null) {
			return ResponseEntity.ok(voluntario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um voluntario")
	public ResponseEntity<Voluntario> apagaVoluntarioControl(@PathVariable Long id) {
		boolean apagar = voluntarioService.apagarVoluntario(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
