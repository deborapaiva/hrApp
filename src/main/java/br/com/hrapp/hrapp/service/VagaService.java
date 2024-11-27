package br.com.hrapp.hrapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.hrapp.hrapp.DTO.VagaDTO;
import br.com.hrapp.hrapp.validators.VagaValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.repository.VagaRepository;

@Service
public class VagaService {
	@Autowired
	private VagaRepository vagaRepository;
	private Vaga vagaAtualizada;

	@Autowired
	private VagaValidator vagaValidator;

	@Autowired
	private ModelMapper modelMapper;

	// Converter Vaga para VagaDTO
	public VagaDTO converterParaDTO(Vaga vaga) {
		return modelMapper.map(vaga, VagaDTO.class);
	}

	// Converter VagaDTO para Vaga
	public Vaga converterParaEntidade(VagaDTO dto) {
		return modelMapper.map(dto, Vaga.class);
	}

	// Buscar todas as vagas como DTOs
	public List<VagaDTO> buscarTodasVagasDTO() {
		List<Vaga> vagas = vagaRepository.findAll();
		return vagas.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}

	//CADASTRAR VAGA
	public Vaga cadastraVaga(@Valid Vaga vaga) {
		return vagaRepository.save(vaga);
	}
	
	//BUSCAR POR TITULO
	public List<Vaga> buscarVagaPorTitulo(String titulo) {
		return vagaRepository.findByTitulo(titulo);
	}
	
	//BUSCAR POR STATUS
	public List<Vaga> buscarVagaPorStatus(String status) {
			return vagaRepository.findByStatus(status);
		}
		
	//BUSCAR POR TODOS
	public List<Vaga> buscarTodasVagas(){
			return vagaRepository.findAll();
		}

	// Deletar uma vaga
	public void deletarVaga(Long id) {
		vagaValidator.validarVagaId(id);
		Optional<Vaga> vagaExistente = vagaRepository.findById(id);
	}

	//ATUALIZAR VAGA
	public Vaga atualizarVaga(Long id, Vaga vagaAtualizada) {
		Vaga vagaExistente = vagaValidator.validarVagaEObter(id);

		vagaExistente.setTitulo(vagaAtualizada.getTitulo());
		vagaExistente.setDescricao(vagaAtualizada.getDescricao());
		vagaExistente.setSalario(vagaAtualizada.getSalario());
		vagaExistente.setStatus(vagaAtualizada.getStatus());

		return vagaRepository.save(vagaExistente);
	}
}

	

