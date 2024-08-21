package med.voll.api.controller;

import med.voll.api.dto.AtualizarPaciente;
import med.voll.api.dto.CadastrarEndereco;
import med.voll.api.dto.CadastrarPaciente;
import med.voll.api.dto.ListagemPacientes;
import med.voll.api.jpa.PacienteJPA;
import med.voll.api.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PacienteControllerTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteController pacienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrar() {
        CadastrarPaciente cadastrarPaciente = new CadastrarPaciente(
                "Nome",
                "email@exemplo.com",
                "12-123456789",
                "09979582430",
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")
        );
        PacienteJPA paciente = new PacienteJPA(cadastrarPaciente);

        pacienteController.cadastrar(cadastrarPaciente);

        verify(pacienteRepository, times(1)).save(any(PacienteJPA.class));
    }

    @Test
    void testListar() {
        Pageable paginacao = PageRequest.of(0, 10);
        PacienteJPA paciente = new PacienteJPA(new CadastrarPaciente(
                "Nome",
                "email@exemplo.com",
                "12-123456789",
                "09979582430",
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")
        ));
        Page<PacienteJPA> page = new PageImpl<>(Collections.singletonList(paciente), paginacao, 1);

        when(pacienteRepository.findAllByAtivoTrue(paginacao)).thenReturn(page);

        Page<ListagemPacientes> response = pacienteController.listar(paginacao);

        assertEquals(1, response.getTotalElements());
        verify(pacienteRepository, times(1)).findAllByAtivoTrue(paginacao);
    }

    @Test
    void testAtualizar() {
        AtualizarPaciente atualizarPaciente = new AtualizarPaciente(
                1L,
                "Nome Atualizado",
                "Telefone Atualizado",
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")
        );
        PacienteJPA paciente = new PacienteJPA(new CadastrarPaciente(
                "Nome",
                "email@exemplo.com",
                "12-123456789",
                "09979582430",
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")
        ));

        when(pacienteRepository.getReferenceById(1L)).thenReturn(paciente);

        pacienteController.atualizar(atualizarPaciente);

        verify(pacienteRepository, times(1)).getReferenceById(1L);
        // Verifique as mudanças nas informações do paciente
    }

    @Test
    void testRemover() {
        PacienteJPA paciente = new PacienteJPA(new CadastrarPaciente(
                "Nome",
                "email@exemplo.com",
                "12-123456789",
                "09979582430",
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")
        ));

        when(pacienteRepository.getReferenceById(1L)).thenReturn(paciente);

        pacienteController.remover(1L);

        verify(pacienteRepository, times(1)).getReferenceById(1L);
        // Verifique a inativação do paciente
    }
}
