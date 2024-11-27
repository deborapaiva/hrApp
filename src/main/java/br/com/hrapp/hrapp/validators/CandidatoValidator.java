package br.com.hrapp.hrapp.validators;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidatoValidator {
    @Autowired
    private CandidatoRepository candidatoRepository;

    public void validarCpfUnico(String cpf){
        if(candidatoRepository.findByCpf(cpf) != null){
            throw new IllegalArgumentException("Candidato com o CPF "+ cpf + " já está cadastrado.");
        }
    }

    public void validarCandidatoExistente(String cpf){
        if(candidatoRepository.findByCpf(cpf) == null){
            throw new IllegalArgumentException("Candidado não encontrado");
        }
    }

    public void validarCandidatoId(Long id){
        if(candidatoRepository.findById(id) == null){
            throw new IllegalArgumentException("Candidato não encontrado");
        }
    }

    public Candidato validarCandidatoEObter(Long id) {
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato com ID " + id + " não encontrada"));
    }
}
