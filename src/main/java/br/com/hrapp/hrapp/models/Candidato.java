package br.com.hrapp.hrapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

	@NoArgsConstructor // Construtor sem argumentos
	@AllArgsConstructor // Construtor com argumentos
	@Entity
	@Data
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

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public @NotNull(message = "Nome completo não pode ser nulo") String getNomeCompleto() {
			return nomeCompleto;
		}

		public void setNomeCompleto(@NotNull(message = "Nome completo não pode ser nulo") String nomeCompleto) {
			this.nomeCompleto = nomeCompleto;
		}

		public @NotNull(message = "Idade não pode ser nula") Integer getIdade() {
			return idade;
		}

		public void setIdade(@NotNull(message = "Idade não pode ser nula") Integer idade) {
			this.idade = idade;
		}

		public @NotNull(message = "CPF não pode ser nulo") String getCpf() {
			return cpf;
		}

		public void setCpf(@NotNull(message = "CPF não pode ser nulo") String cpf) {
			this.cpf = cpf;
		}

		public @NotNull(message = "Email não pode ser nulo") String getEmail() {
			return email;
		}

		public void setEmail(@NotNull(message = "Email não pode ser nulo") String email) {
			this.email = email;
		}

		public @NotNull(message = "Telefone não pode ser nulo") String getTelefone() {
			return telefone;
		}

		public void setTelefone(@NotNull(message = "Telefone não pode ser nulo") String telefone) {
			this.telefone = telefone;
		}
	}




