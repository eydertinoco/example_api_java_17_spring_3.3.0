package med.voll.api.record;

public record Paciente(
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
}
