package med.voll.api.controller;

import med.voll.api.jpa.MedicoJPA;
import med.voll.api.record.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void cadastrarMedico(@RequestBody Medico dadosMedico) {
        medicoRepository.save(new MedicoJPA(dadosMedico));
    }
}
