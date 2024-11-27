package br.com.hrapp.hrapp.validators;

import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VagaValidator {
    @Autowired
    private VagaRepository vagaRepository;

    public void validarVagaId(Long id){
        if(vagaRepository.findById(id) == null){
            throw new IllegalArgumentException("Vaga não encontrada");
        }
    }

    public Vaga validarVagaEObter(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaga com ID " + id + " não encontrada"));
    }


}
