package br.com.hrapp.hrapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.service.VagaService;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    // CADASTRAR VAGA 
    @PostMapping("/cadastrar")
    public Vaga cadastraVaga(@RequestBody Vaga vaga) {
        return vagaService.cadastraVaga(vaga);
    }

    // BUSCAR VAGA POR TÍTULO
    @GetMapping("/titulo/{titulo}")
    public List<Vaga> buscarVagaPorTitulo(@PathVariable String titulo) {
        return vagaService.buscarVagaPorTitulo(titulo);
    }

    // BUSCAR VAGA POR STATUS
    @GetMapping("/status/{status}")
    public List<Vaga> buscarVagaPorStatus(@PathVariable String status) {
        return vagaService.buscarVagaPorStatus(status);
    }
    
    //BUSCAR TODAS AS VAGAS
    @GetMapping("/todas")
    public List<Vaga> buscarTodasVagas(){
    	return vagaService.buscarTodasVagas();
    }

    // ATUALIZAR VAGA
    @PutMapping("/{id}")
    public Vaga atualizarVaga(
            @PathVariable Long id,
            @RequestBody Vaga vagaAtualizada) {
        return vagaService.atualizarVaga(id, vagaAtualizada);
    }

    // EXCLUIR VAGA
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }

}
