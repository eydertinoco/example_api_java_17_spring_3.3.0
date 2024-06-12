package med.voll.api.controller;

import med.voll.api.record.Medico;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @PostMapping
    public void cadastrarMedico(@RequestBody Medico medico) {
        System.out.println(medico);
    }
}
