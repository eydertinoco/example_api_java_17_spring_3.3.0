package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.jpa.MedicoJPA;
import med.voll.api.dto.ListagemMedicos;
import med.voll.api.dto.CadastrarMedico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid CadastrarMedico dadosMedico) {
        medicoRepository.save(new MedicoJPA(dadosMedico));
    }

    @GetMapping
    public List<ListagemMedicos> listarMedicos() {
        return medicoRepository.findAll().stream().map(ListagemMedicos::new).toList();
    }
}
