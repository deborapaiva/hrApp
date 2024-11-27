package br.com.hrapp.hrapp.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.JMRuntimeException;

import br.com.hrapp.hrapp.DTO.CandidatoDTO;
import br.com.hrapp.hrapp.validators.CandidatoValidator;
import br.com.hrapp.hrapp.validators.VagaValidator;
import org.modelmapper.ModelMapper;
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

	@Autowired
	private CandidatoValidator candidatoValidator;

	@Autowired
	private VagaValidator vagaValidator;

    @Autowired
    private ModelMapper modelMapper;

    // Converter Candidato para CandidatoDTO
    public CandidatoDTO converterParaDTO(Candidato candidato) {
        return modelMapper.map(candidato, CandidatoDTO.class);
    }

    // Converter CandidatoDTO para Candidato
    public Candidato converterParaEntidade(CandidatoDTO dto) {
        return modelMapper.map(dto, Candidato.class);
    }

    // Buscar todos os candidatos e retornar como DTOs
    public List<CandidatoDTO> buscarTodosCandidatos() {
        List<Candidato> candidatos = candidatoRepository.findAll();
        return candidatos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }


	//CADASTRAR CANDIDATO
	public Candidato cadastrarCandidato(@Valid Candidato candidato) {
		candidatoValidator.validarCpfUnico(candidato.getCpf());
		return candidatoRepository.save(candidato);
	}
	
	//BUSCAR POR CPF
	public Candidato buscarCandidatoPorCPF(String cpf) {
		return candidatoRepository.findByCpf(cpf);
	}

	// DELETAR POR CPF
	public void deletarCandidato(String cpf) {
		candidatoValidator.validarCandidatoExistente(cpf);
		Candidato candidatoExistente = candidatoRepository.findByCpf(cpf);
		candidatoRepository.delete(candidatoExistente);
	}

	//CADASTRAR CANDIDATO NA VAGA
	public Candidato associarCandidatoAVaga(Long candidatoId, Long vagaId) {

		Candidato candidato = candidatoValidator.validarCandidatoEObter(candidatoId);

		Vaga vaga = vagaValidator.validarVagaEObter(vagaId);

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

	public List<Candidato> buscarTodosCandidatosDTO() {
		return candidatoRepository.findAll();
	}
}

