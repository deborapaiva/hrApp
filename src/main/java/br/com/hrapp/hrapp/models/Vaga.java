package br.com.hrapp.hrapp.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Vaga {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vaga_id")
	private Long id;
	
	@NotNull
	private String titulo;
	
	@NotNull
	private BigDecimal salario;
	
	@NotNull
	private String status;
	
	@NotNull
	private String descricao;
	
	//REFERÊNCIA PARA CANDIDATO
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "tb_vaga_candidato",
			joinColumns = @JoinColumn(name = "vaga_id"),
			inverseJoinColumns = @JoinColumn(name = "candidato_id")
	)
	private List<Candidato> candidatos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	
}
