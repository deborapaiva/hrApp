package br.com.hrapp.hrapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.models.Vaga;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
	Candidato findById(long id);
	Candidato findByCpf(String cpf);
	List<Candidato>findByNomeCompleto(String nomeCompleto);


	Iterable<Candidato>findByVagas(Vaga vaga);
}
