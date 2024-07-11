package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import med.voll.api.enums.Especialidade;
import med.voll.api.jpa.EnderecoJPA;
import med.voll.api.jpa.MedicoJPA;

public record DetalhamentoMedico(
    Long id,
    String nome,
    @Email
    String email,
    @Pattern(regexp = "^\\d{2}-\\d{9}$")
    String telefone,
    @Pattern(regexp = "\\d{4,6}")
    String crm,
    Especialidade especialidade,
    @Valid
    EnderecoJPA endereco
){
    public DetalhamentoMedico(MedicoJPA medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEnderecoJPA());
    }
}
