package br.com.hrapp.hrapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
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

		// RELAÇÃO MANY TO MANY COM VAGA
		@ManyToMany(mappedBy = "candidatos")
		@JsonManagedReference // Este lado será serializado
		private List<Vaga> vagas;

		public List<Vaga> getVagas() {
			return vagas;
		}

		public void setVagas(List<Vaga> vagas) {
			this.vagas = vagas;
		}

		// Getters e Setters
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




