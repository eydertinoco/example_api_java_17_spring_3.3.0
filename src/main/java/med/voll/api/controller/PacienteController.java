package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.jpa.PacienteJPA;
import med.voll.api.dto.CadastrarPaciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid CadastrarPaciente paciente) {
        pacienteRepository.save(new PacienteJPA(paciente));
    }
}
