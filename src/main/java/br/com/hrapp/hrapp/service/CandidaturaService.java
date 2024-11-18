package br.com.hrapp.hrapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.models.Candidatura;
import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.repository.CandidaturaRepository;
import br.com.hrapp.hrapp.repository.VagaRepository;


@Service
public class CandidaturaService {
  @Autowired
  private CandidaturaRepository candidaturaRepository;
  
  @Autowired
  private VagaRepository vagaRepository;
  
  public Candidatura cadastrarCandidatoEmVaga(Candidato candidato, Long vagaId) {
	    Vaga vaga = vagaRepository.findById(vagaId)
	        .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

	    Candidatura candidatura = new Candidatura();
	    candidatura.setCandidato(candidato);
	    candidatura.setVaga(vaga);

	    return candidaturaRepository.save(candidatura);
	}
  
  
}
