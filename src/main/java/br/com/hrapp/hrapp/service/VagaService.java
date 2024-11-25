package br.com.hrapp.hrapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.repository.VagaRepository;

@Service
public class VagaService {
	@Autowired
	private VagaRepository vagaRepository;
	private Vaga vagaAtualizada;


	//CADASTRAR
	public Vaga cadastraVaga(Vaga vaga) {
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
		Optional<Vaga> vagaExistente = vagaRepository.findById(id);

		if (vagaExistente.isPresent()) {
			vagaRepository.deleteById(id);
		} else {
			throw new RuntimeException("Vaga com ID " + id + " não encontrada.");
		}
	}

	//ATUALIZAR VAGA
	public Vaga atualizarVaga(Long id, Vaga vagaAtualizada) {
		Vaga vagaExistente = vagaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vaga com ID " + id + " não encontrada"));

		vagaExistente.setTitulo(vagaAtualizada.getTitulo());
		vagaExistente.setDescricao(vagaAtualizada.getDescricao());
		vagaExistente.setSalario(vagaAtualizada.getSalario());
		vagaExistente.setStatus(vagaAtualizada.getStatus());

		return vagaRepository.save(vagaExistente);
	}
}

	

