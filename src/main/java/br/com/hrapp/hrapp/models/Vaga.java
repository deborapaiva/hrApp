package br.com.hrapp.hrapp.models;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Vaga")
@AllArgsConstructor // Construtor com argumentos
@Data
@Builder
public class Vaga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vaga_id")
	private Long id;

	@NotNull
	private String titulo;

	private BigDecimal salario;

	@NotNull
	private String status;

	@NotNull
	private String descricao;

	// Construtor vazio obrigatório para o Hibernate
	public Vaga() {
	}

	// Construtor personalizado
	public Vaga(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "tb_candidato_vaga",
			joinColumns = @JoinColumn(name = "vaga_id"),
			inverseJoinColumns = @JoinColumn(name = "candidato_id")
	)
	private List<Candidato> candidatos;

	@JsonCreator
	public <candidatos> Vaga(
			@JsonProperty("titulo") String titulo,
			@JsonProperty("salario") BigDecimal salario,
			@JsonProperty("status") String status,
			@JsonProperty("descricao") String descricao
			) {
		this.titulo = titulo;
		this.salario = salario;
		this.status = status;
		this.descricao = descricao;
	}


}
