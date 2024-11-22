package br.com.hrapp.hrapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Set;

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

	}




