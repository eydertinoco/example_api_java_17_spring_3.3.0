package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarPaciente;
import med.voll.api.jpa.PacienteJPA;
import med.voll.api.dto.ListagemPacientes;
import med.voll.api.dto.CadastrarPaciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid CadastrarPaciente paciente) {
        pacienteRepository.save(new PacienteJPA(paciente));
    }

    @GetMapping
    public Page<ListagemPacientes> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(ListagemPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarPaciente dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }
}
