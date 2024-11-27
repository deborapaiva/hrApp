package br.com.hrapp.hrapp.DTO;

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
public class VagaDTO implements Serializable {

    private String titulo;
    private String status;
    private String descricao;


    private List<String> candidatos;
}
