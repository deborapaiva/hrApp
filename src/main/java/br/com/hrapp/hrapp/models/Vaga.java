package br.com.hrapp.hrapp.models;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "tb_candidato_vaga",
			joinColumns = @JoinColumn(name = "vaga_id"),
			inverseJoinColumns = @JoinColumn(name = "candidato_id")
	)
	private Set<Candidato> candidatos;

	// Construtor padrão
	public Vaga() {
		this.candidatos = null;  // Inicializa candidatos como null ou um Set vazio
	}

	// Construtor com parâmetros, usado pelo Jackson
	@JsonCreator
	public Vaga(
			@JsonProperty("titulo") String titulo,
			@JsonProperty("salario") BigDecimal salario,
			@JsonProperty("status") String status,
			@JsonProperty("descricao") String descricao,
			@JsonProperty("candidatos") Set<Candidato> candidatos
	) {
		this.titulo = titulo;
		this.salario = salario;
		this.status = status;
		this.descricao = descricao;
		this.candidatos = candidatos;
	}

	// Getters e Setters
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(Set<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
}
