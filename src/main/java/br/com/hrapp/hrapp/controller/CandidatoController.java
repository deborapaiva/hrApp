package br.com.hrapp.hrapp.controller;

import java.util.List;

import br.com.hrapp.hrapp.DTO.CandidatoDTO;
import br.com.hrapp.hrapp.models.Vaga;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.service.CandidatoService;

@RestController
@RequestMapping("/candidatos")
@Tag(name = "Candidato", description ="Controller para Candidato")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;


    //CADASTRAR CANDIDATO
    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar candidato", description = "Cadastrar um novo candidato")
    public CandidatoDTO cadastrarCandidato(@RequestBody CandidatoDTO candidatoDTO) {
        Candidato candidato = candidatoService.converterParaEntidade(candidatoDTO);
        Candidato candidatoSalvo = candidatoService.cadastrarCandidato(candidato);
        return candidatoService.converterParaDTO(candidatoSalvo);
    }

    // BUSCAR CANDIDATO POR CPF
    @GetMapping("/cpf/{cpf}")
    @Operation(
            summary = ("Buscar candidatos por cpf"),
            description = ("Buscar por candidatos por cpf"),
            tags={"Candidato"}
    )
    public Candidato buscarCandidatoPorCPF(@PathVariable String cpf) {
        return candidatoService.buscarCandidatoPorCPF(cpf);
    }
    
    //BUSCAR TODOS CANDIDATOS
    @GetMapping("/todos")
    @Operation(summary = "Buscar todos candidatos", description = "Buscar todos candidatos cadastrados")
    public List<CandidatoDTO> buscarTodosCandidatos() {
        return candidatoService.buscarTodosCandidatos();
    }

    // DELETAR POR CPF
    @DeleteMapping("/delete/{cpf}")
    @Operation(
            summary = ("Deletar candidato"),
            description = ("Deletar Vaga por CPF"),
            tags={"Candidato"}
    )
    public ResponseEntity<Void> deletarCandidato(@PathVariable String cpf) {
        candidatoService.deletarCandidato(cpf);
        return ResponseEntity.noContent().build();
    }

    //ADICIONAR CANDIDATO EXISTENTE A VAGA EXISTENTE
    @PostMapping("/inscricao")
    @Operation(
            summary = ("Inscrever Candidato em Vaga"),
            description = ("Inscrever candidato em Vaga existente por Id"),
            tags={"Candidato"}
    )
    public ResponseEntity<Candidato> associarCandidatoAVaga(
            @RequestParam Long candidatoId,
            @RequestParam Long vagaId) {
        Candidato candidato = candidatoService.associarCandidatoAVaga(candidatoId, vagaId);
        return ResponseEntity.ok(candidato);
    }
}
