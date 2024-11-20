package br.com.hrapp.hrapp.controller;

import java.util.List;

import br.com.hrapp.hrapp.models.Vaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.service.CandidatoService;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;
    private Object candidatoRepository;

    //CADASTRAR CANDIDATO
    @PostMapping("/cadastrar")
    public Candidato cadastrarCandidato(@RequestBody Candidato candidato) {
        return candidatoService.cadastrarCandidato(candidato);
    }

    // BUSCAR CANDIDATO POR CPF
    @GetMapping("/cpf/{cpf}")
    public Candidato buscarCandidatoPorCPF(@PathVariable String cpf) {
        return candidatoService.buscarCandidatoPorCPF(cpf);
    }
    
    //BUSCAR TODOS CANDIDATOS
    @GetMapping("/todos")
    public List<Candidato> buscarTodosCandidatos() {

        return candidatoService.buscarTodosCandidatos();
    }

    // DELETAR POR CPF
    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<Void> deletarCandidato(@PathVariable String cpf) {
        candidatoService.deletarCandidato(cpf);
        return ResponseEntity.noContent().build();
    }

    //ADICIONAR CANDIDATO EXISTENTE A VAGA EXISTENTE
    @PostMapping("/inscricao")
    public ResponseEntity<Candidato> associarCandidatoAVaga(
            @RequestParam Long candidatoId,
            @RequestParam Long vagaId) {
        Candidato candidato = candidatoService.associarCandidatoAVaga(candidatoId, vagaId);
        return ResponseEntity.ok(candidato);
    }
}
