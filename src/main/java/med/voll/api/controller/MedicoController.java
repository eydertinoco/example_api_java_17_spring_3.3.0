package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarMedico;
import med.voll.api.dto.DetalhamentoMedico;
import med.voll.api.jpa.MedicoJPA;
import med.voll.api.dto.ListagemMedicos;
import med.voll.api.dto.CadastrarMedico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid CadastrarMedico dadosMedico, UriComponentsBuilder uriBuilder) {
        var medico = new MedicoJPA(dadosMedico);
        medicoRepository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicos>> listarMedicos(@PageableDefault(sort={"nome"}) Pageable paginacao) {
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(ListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid AtualizarMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        //medicoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
