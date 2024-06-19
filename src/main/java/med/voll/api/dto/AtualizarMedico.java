package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.enums.Especialidade;

public record AtualizarMedico(
        @NotNull
        Long id,
        String nome,
        @Pattern(regexp = "^\\d{2}-\\d{9}$")
        String telefone,
        CadastrarEndereco endereco
) {
}
