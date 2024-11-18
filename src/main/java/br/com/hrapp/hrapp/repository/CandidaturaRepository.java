package br.com.hrapp.hrapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hrapp.hrapp.models.Candidatura;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long>  {
	Candidatura  findById(long id );
}
