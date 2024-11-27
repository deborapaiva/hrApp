package br.com.hrapp.hrapp.controller;

import java.util.List;

import br.com.hrapp.hrapp.DTO.VagaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.service.VagaService;

@RestController
@RequestMapping("/vaga")
@Tag(name = "Vaga", description ="Controller para Vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    // CADASTRAR VAGA 
    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar vaga", description = "Cadastrar uma nova vaga")
    public VagaDTO cadastrarVaga(@RequestBody VagaDTO vagaDTO) {
        Vaga vaga = vagaService.converterParaEntidade(vagaDTO);
        Vaga vagaSalva = vagaService.cadastraVaga(vaga);
        return vagaService.converterParaDTO(vagaSalva);
    }

    // BUSCAR VAGA POR TÍTULO
    @GetMapping("/titulo/{titulo}")
    @Operation(
            summary = ("Busca de Vagas por Título"),
            description = ("Busca de Vagas por Título"),
            tags={"Vaga"}
    )
    public List<Vaga> buscarVagaPorTitulo(@PathVariable String titulo) {
        return vagaService.buscarVagaPorTitulo(titulo);
    }

    // BUSCAR VAGA POR STATUS
    @GetMapping("/status/{status}")
    @Operation(
            summary = ("Busca de Vagas por Status"),
            description = ("Busca de Vagas por Status"),
            tags={"Vaga"}
    )
    public List<Vaga> buscarVagaPorStatus(@PathVariable String status) {
        return vagaService.buscarVagaPorStatus(status);
    }
    
    //BUSCAR TODAS AS VAGAS
    @GetMapping("/todas")
    @Operation(summary = "Buscar todas as vagas", description = "Buscar todas as vagas disponíveis")
    public List<VagaDTO> buscarTodasVagas() {
        return vagaService.buscarTodasVagasDTO();
    }

    // ATUALIZAR VAGA
    @PutMapping("/{id}")
    @Operation(
            summary = ("Atualização de Vaga"),
            description = ("Atualização de Vaga"),
            tags={"Vaga"}
      )
    public Vaga atualizarVaga(
            @PathVariable Long id,
            @RequestBody Vaga vagaAtualizada) {
        return vagaService.atualizarVaga(id, vagaAtualizada);
    }

    // EXCLUIR VAGA
    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = ("Exclusão de Vaga"),
            description = ("Excluir de Vaga por ID"),
            tags={"Vaga"}
    )
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }

}
