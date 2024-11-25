package br.com.hrapp.hrapp.service;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.repository.VagaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VagaServiceTest {

    @Mock
    private VagaRepository vagaRepository;
    @InjectMocks
    private VagaService vagaService;

    private Vaga vaga;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        vaga = new Vaga();
        vaga.setId(2L);
        vaga.setTitulo("Desenvolvedor Java");
        vaga.setDescricao("Vaga para desenvolvimento backend.");
        vaga.setStatus("Ativo");
        vaga.setSalario(BigDecimal.valueOf(2500));
        vaga.setCandidatos(new ArrayList<>());
    }


    @Test
    @DisplayName("Cadastro de Vagas com sucesso")
    void cadastraVagaComSucesso() {
        when(vagaRepository.save(any(Vaga.class))).thenReturn(vaga);

        Vaga vagaSalva = vagaService.cadastraVaga(vaga);

        assertNotNull(vagaSalva); //Validação Nulo

        verify(vagaRepository, times(1)).save(vagaSalva);
    }

    @Test
    @DisplayName("Atualização de Vagas não encontradas")
    void updateVagaErro()  {
        when(vagaRepository.findById(vaga.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            vagaService.atualizarVaga(vaga.getId(), new Vaga());
        });

        assertEquals("Vaga com ID " + vaga.getId() +  " não encontrada", exception.getMessage());
        verify(vagaRepository, never()).save(any(Vaga.class));

    }
}