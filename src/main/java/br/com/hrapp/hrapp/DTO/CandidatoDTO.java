package br.com.hrapp.hrapp.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoDTO implements Serializable {

    private String nomeCompleto;
    private String cpf;

    @JsonIgnore
    private List<String> vagas;

}
