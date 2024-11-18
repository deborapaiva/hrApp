package br.com.hrapp.hrapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hrapp.hrapp.models.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
	Vaga findById(long id);
	List<Vaga> findByTitulo(String titulo);
	List<Vaga> findByStatus(String status);
}
