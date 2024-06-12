package med.voll.api.record;

import med.voll.api.enums.Especialidade;

public record Medico(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
}
