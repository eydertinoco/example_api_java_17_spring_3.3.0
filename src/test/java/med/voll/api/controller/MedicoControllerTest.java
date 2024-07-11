package med.voll.api.controller;

import med.voll.api.dto.*;
import med.voll.api.enums.Especialidade;
import med.voll.api.jpa.MedicoJPA;
import med.voll.api.repository.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MedicoControllerTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoController medicoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarMedico() {
        CadastrarMedico cadastrarMedico = new CadastrarMedico(
                "Nome",
                "email@exemplo.com",
                "12-123456789",
                "123456",
                Especialidade.CARDIOLOGIA,
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")
        );
        MedicoJPA medico = new MedicoJPA(cadastrarMedico);

        when(medicoRepository.save(any(MedicoJPA.class))).thenReturn(medico);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        ResponseEntity<?> response = medicoController.cadastrarMedico(cadastrarMedico, uriBuilder);

        assertEquals(201, response.getStatusCodeValue());
        verify(medicoRepository, times(1)).save(any(MedicoJPA.class));
    }

    @Test
    void testListarMedicos() {
        PageRequest paginacao = PageRequest.of(0, 10);
        MedicoJPA medico = new MedicoJPA(new CadastrarMedico(
                "Nome",
                "email@exemplo.com",
                "12-123456789",
                "123456",
                Especialidade.CARDIOLOGIA,
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")
        ));
        Page<MedicoJPA> page = new PageImpl<>(Collections.singletonList(medico), paginacao, 1);

        when(medicoRepository.findAllByAtivoTrue(paginacao)).thenReturn(page);

        ResponseEntity<Page<ListagemMedicos>> response = medicoController.listarMedicos(paginacao);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getTotalElements());
        verify(medicoRepository, times(1)).findAllByAtivoTrue(paginacao);
    }

    @Test
    void testAtualizarMedico() {
        AtualizarMedico atualizarMedico = new AtualizarMedico(
                1L,
                "Nome Atualizado",
                "Telefone Atualizado",
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")

        );
        MedicoJPA medico = new MedicoJPA(new CadastrarMedico(
                "Nome",
                "email@exemplo.com",
                "12-123456789",
                "123456",
                Especialidade.CARDIOLOGIA,
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")
        ));

        when(medicoRepository.getReferenceById(1L)).thenReturn(medico);

        ResponseEntity<?> response = medicoController.atualizarMedico(atualizarMedico);

        assertEquals(200, response.getStatusCodeValue());
        verify(medicoRepository, times(1)).getReferenceById(1L);
        // Verifique as mudanças nas informações do médico
    }

    @Test
    void testExcluir() {
        MedicoJPA medico = new MedicoJPA(new CadastrarMedico(
                "Nome",
                "email@exemplo.com",
                "12-123456789",
                "123456",
                Especialidade.CARDIOLOGIA,
                new CadastrarEndereco("Rua A", "Cidade B", "Estado C", "12345-678", "Bairro D", "123", "Complemento E")
        ));

        when(medicoRepository.getReferenceById(1L)).thenReturn(medico);

        ResponseEntity<?> response = medicoController.excluir(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(medicoRepository, times(1)).getReferenceById(1L);
        // Verifique a exclusão lógica do médico
    }
}
