package br.com.hrapp.hrapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

@Entity
@Table(name="Candidato")
public class Candidato {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private UUID id;
		
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
		@ManyToOne
		@JoinColumn(name ="vaga_id")
		private Vaga vaga;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

		public Vaga getVaga() {
			return vaga;
		}

		public void setVaga(Vaga vaga) {
			this.vaga = vaga;
		}
}


