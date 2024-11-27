package br.com.hrapp.hrapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

	@NoArgsConstructor // Construtor sem argumentos
	@AllArgsConstructor // Construtor com argumentos
	@Builder //criação de objetos complexos
	@Data //Getter and Setter
	@Table(name="Candidato")
	@Entity
	public class Candidato {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "candidato_id")
		private Long id;

		@NotNull(message = "Nome completo não pode ser nulo")
		private String nomeCompleto;

		private Integer idade;

		@NotNull(message = "CPF não pode ser nulo")
		private String cpf;

		private String email;

		private String telefone;

		// RELAÇÃO MANY TO MANY COM VAGA
		@ManyToMany(mappedBy = "candidatos")
		@JsonIgnore // Ignora a serialização do lado ManyToMany para evitar loops
		private List<Vaga> vagas;


	}




