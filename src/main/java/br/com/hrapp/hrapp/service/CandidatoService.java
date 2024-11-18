package br.com.hrapp.hrapp.service;

import java.util.List;

import javax.management.JMRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.models.Candidatura;
import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.repository.CandidatoRepository;
import br.com.hrapp.hrapp.repository.CandidaturaRepository;
import br.com.hrapp.hrapp.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class CandidatoService {
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private VagaRepository vagaRepository;
	
	//CADASTRAR CANDIDATO
	public Candidato cadastrarCandidato(@Valid Candidato candidato) {
	    return candidatoRepository.save(candidato);
	}
	
	//CADASTRAR CANDIDATO NA VAGA
	public Candidato cadastrarCandidatoEmVaga(Candidato candidato, Long vagaId) {
		Vaga vaga = vagaRepository.findById(vagaId).orElseThrow(() -> new JMRuntimeException("Vaga não encontrada"));
	    
		candidato.setVaga(vaga);
		return candidatoRepository.save(candidato);
	}
	
	//BUSCAR POR CPF
	public Candidato buscarCandidatoPorCPF(String cpf) {
	    return candidatoRepository.findByCpf(cpf);
	    }

	public List<Candidato> buscarTodosCandidatos() {
		return candidatoRepository.findAll();
	}


	// DELETAR POR CPF
	public void deletarCandidato(String cpf) {
		Candidato candidatoExistente = candidatoRepository.findByCpf(cpf);

		if (candidatoExistente != null) {
			candidatoRepository.delete(candidatoExistente);
		} else {
			throw new RuntimeException("Candidato com CPF " + cpf + " não encontrado.");
		}
	}

}

