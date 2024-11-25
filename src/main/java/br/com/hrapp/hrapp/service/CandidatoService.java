package br.com.hrapp.hrapp.service;

import java.util.List;

import javax.management.JMRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.repository.CandidatoRepository;
import br.com.hrapp.hrapp.repository.VagaRepository;
import jakarta.validation.Valid;

@Service
public class CandidatoService {
	@Autowired
	private CandidatoRepository candidatoRepository;

	@Autowired
	private VagaRepository vagaRepository;

	//CADASTRAR CANDIDATO
	public Candidato cadastrarCandidato(@Valid Candidato candidato) {
	    if(candidatoRepository.findByCpf(candidato.getCpf()) != null){
			throw new IllegalArgumentException("Candidato com o CPF "+ candidato.getCpf() + " já está cadastrado.");
		}
		return candidatoRepository.save(candidato);
	}
	
	//BUSCAR POR CPF
	public Candidato buscarCandidatoPorCPF(String cpf) {

		return candidatoRepository.findByCpf(cpf);
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

	//CADASTRAR CANDIDATO NA VAGA
	public Candidato associarCandidatoAVaga(Long candidatoId, Long vagaId) {

		Candidato candidato = candidatoRepository.findById(candidatoId)
				.orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

		Vaga vaga = vagaRepository.findById(vagaId)
				.orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

		// Adiciona a vaga à lista de vagas do candidato
		List<Vaga> vagasDoCandidato = candidato.getVagas();
		vagasDoCandidato.add(vaga);
		candidato.setVagas(vagasDoCandidato);

		// Também adiciona o candidato à lista de candidatos da vaga
		List<Candidato> candidatosDaVaga = vaga.getCandidatos();
		candidatosDaVaga.add(candidato);
		vaga.setCandidatos(candidatosDaVaga);

		vagaRepository.save(vaga); // Atualiza a vaga
		return candidatoRepository.save(candidato); // Atualiza o candidato
	}

	//BUSCAR CANDIDATO POR VAGA
	public Iterable<Candidato> buscarCandidatosPorVaga(Vaga vaga) {
		return candidatoRepository.findByVagas(vaga);
	}


	public List<Candidato> buscarTodosCandidatos() {
		return candidatoRepository.findAll();
	}
}

