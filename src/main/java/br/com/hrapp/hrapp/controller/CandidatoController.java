package br.com.hrapp.hrapp.controller;

import java.util.List;
import java.util.Optional;

import br.com.hrapp.hrapp.models.Vaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.models.Candidatura;
import br.com.hrapp.hrapp.service.CandidatoService;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;
    private Object candidatoRepository;

    //CADASTRAR
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
    
    //CADASTRAR CANDIDATOS NAS VAGAS
    @PostMapping("/cadastrar/vaga/{vagaId}")
    public Candidato cadastrarCandidatoEmVaga(@RequestBody Candidato candidato, @PathVariable Long vagaId) {
        return candidatoService.cadastrarCandidatoEmVaga(candidato, vagaId);
    }

    // DELETAR POR CPF
    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<Void> deletarCandidato(@PathVariable String cpf) {
        candidatoService.deletarCandidato(cpf);
        return ResponseEntity.noContent().build();
    }


}
