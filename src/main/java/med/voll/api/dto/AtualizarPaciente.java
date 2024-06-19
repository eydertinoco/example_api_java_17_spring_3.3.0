package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizarPaciente(
        @NotNull
        Long id,
        String nome,
        @Pattern(regexp = "^\\d{2}-\\d{9}$")
        String telefone,
        @Valid
        CadastrarEndereco endereco
) {
}
