package br.com.hrapp.hrapp.service;

import br.com.hrapp.hrapp.models.Candidato;
import br.com.hrapp.hrapp.models.Vaga;
import br.com.hrapp.hrapp.repository.CandidatoRepository;
import br.com.hrapp.hrapp.repository.VagaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CandidatoServiceTest {

    @Mock
    private CandidatoRepository candidatoRepository;

    @Mock
    private VagaRepository vagaRepository;

    @InjectMocks
    private CandidatoService candidatoService;

    private Candidato candidato;
    private Vaga vaga;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        //Configuração dos objetos
        candidato = new Candidato();
        candidato.setId(1L);
        candidato.setNomeCompleto("João Silva");
        candidato.setIdade(30);
        candidato.setCpf("12345678901");
        candidato.setEmail("joao@email.com");
        candidato.setTelefone("999999999");

        vaga = new Vaga();
        vaga.setId(2L);
        vaga.setTitulo("Desenvolvedor Java");
        vaga.setDescricao("Vaga para desenvolvimento backend.");
        vaga.setCandidatos(new ArrayList<>());
    }

    @AfterEach
    void tearDown() {
        // Garantindo isolamento entre testes
        clearInvocations(candidatoRepository, vagaRepository);
        reset(candidatoRepository, vagaRepository);
    }

    @Test
    @DisplayName("Cadastro de candidatos com sucesso")
    void cadastrarCandidatoSuccess() {
        when(candidatoRepository.save(any(Candidato.class))).thenReturn(candidato);

        Candidato candidatoSalvo = candidatoService.cadastrarCandidato(candidato);

        assertNotNull(candidatoSalvo); //Validação de nulo

        assertEquals(candidato.getNomeCompleto(), candidatoSalvo.getNomeCompleto());
        assertEquals(candidato.getCpf(), candidatoSalvo.getCpf());
        assertEquals(candidato.getEmail(), candidatoSalvo.getEmail());
        assertEquals(candidato.getTelefone(), candidatoSalvo.getTelefone());

        verify(candidatoRepository, times(1)).save(candidato);
    }

    @Test
    @DisplayName("Cadastro de candidatos com erro")
    void cadastrarCandidatoErro() {
        when(candidatoRepository.save(any(Candidato.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            candidatoService.cadastrarCandidato(candidato);
        });

        assertEquals("Erro ao salvar", exception.getMessage());

        verify(candidatoRepository, times(1)).save(candidato);
    }


    @Test
    @DisplayName("Cadastro de candidatos com sucesso em vagas")
    void associarCandidatoAVagaSuccess() {
        when(candidatoRepository.findById(candidato.getId())).thenReturn(Optional.of(candidato));
        when(vagaRepository.findById(vaga.getId())).thenReturn(Optional.of(vaga));

        candidato.setVagas(new ArrayList<>());
        vaga.setCandidatos(new ArrayList<>());

        Candidato success = candidatoService.associarCandidatoAVaga(candidato.getId(), vaga.getId());

        assertTrue(candidato.getVagas().contains(vaga));
        assertTrue(vaga.getCandidatos().contains(candidato));

        verify(candidatoRepository, times(1)).save(candidato);
        verify(vagaRepository, times(1)).save(vaga);
    }

    @Test
    @DisplayName("Cadastro de candidatos com erro em vagas")
    void associarCandidatoAVagaErro1() {
        when(candidatoRepository.findById(candidato.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            candidatoService.associarCandidatoAVaga(candidato.getId(), vaga.getId());
        });

        assertEquals("Candidato não encontrado", exception.getMessage());
        verify(vagaRepository, never()).save(any(Vaga.class));
    }

    @Test
    @DisplayName("Cadastro de candidatos em vagas com erro")
    void associarCandidatoAVagaErro2() {
        when(candidatoRepository.findById(candidato.getId())).thenReturn(Optional.of(candidato));
        when(vagaRepository.findById(vaga.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            candidatoService.associarCandidatoAVaga(candidato.getId(), vaga.getId());
        });

        assertEquals("Vaga não encontrada", exception.getMessage());
        verify(vagaRepository, never()).save(any(Vaga.class));
    }
}