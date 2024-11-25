package br.com.hrapp.hrapp.controller;

import java.util.List;

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
    @Operation(
            summary = ("Cadastro de Vagas"),
            description = ("Cadastro das vagas disponíveis pelo RH"),
            tags={"vaga"}
    )
    public Vaga cadastraVaga(@RequestBody Vaga vaga) {
        return vagaService.cadastraVaga(vaga);
    }

    // BUSCAR VAGA POR TÍTULO
    @GetMapping("/titulo/{titulo}")
    @Operation(
            summary = ("Busca de Vagas por Título"),
            description = ("Busca de Vagas por Título"),
            tags={"vaga"}
    )
    public List<Vaga> buscarVagaPorTitulo(@PathVariable String titulo) {
        return vagaService.buscarVagaPorTitulo(titulo);
    }

    // BUSCAR VAGA POR STATUS
    @GetMapping("/status/{status}")
    @Operation(
            summary = ("Busca de Vagas por Status"),
            description = ("Busca de Vagas por Status"),
            tags={"vaga"}
    )
    public List<Vaga> buscarVagaPorStatus(@PathVariable String status) {
        return vagaService.buscarVagaPorStatus(status);
    }
    
    //BUSCAR TODAS AS VAGAS
    @GetMapping("/todas")
    @Operation(
            summary = ("Busca de Todas as Vagas"),
            description = ("Busca de Todas as Vagas"),
            tags={"vaga"}
    )
    public List<Vaga> buscarTodasVagas(){
    	return vagaService.buscarTodasVagas();
    }

    // ATUALIZAR VAGA
    @PutMapping("/{id}")
    @Operation(
            summary = ("Atualização de Vaga"),
            description = ("Atualização de Vaga"),
            tags={"vaga"}
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
            tags={"vaga"}
    )
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }

}
