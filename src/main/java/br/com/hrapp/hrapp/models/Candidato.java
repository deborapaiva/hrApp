package br.com.hrapp.hrapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name="Candidato")
public class Candidato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidato_id")
	private Long id;

	@NotNull(message = "Nome completo não pode ser nulo")
	private String nomeCompleto;

	@NotNull(message = "Idade não pode ser nula")
	private Integer idade;

	@NotNull(message = "CPF não pode ser nulo")
	private String cpf;

	@NotNull(message = "Email não pode ser nulo")
	private String email;

	@NotNull(message = "Telefone não pode ser nulo")
	private String telefone;


	//REFERÊNCIA PARA VAGA
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(
			name = "tb_candidato_vaga",
			joinColumns = @JoinColumn(name = "candidato_id"),
			inverseJoinColumns = @JoinColumn(name = "vaga_id")
	)

	private Set<Vaga> vaga;

	public Set<Vaga> getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = (Set<Vaga>) vaga;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}



