package med.voll.api.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AtualizarPaciente;
import med.voll.api.dto.CadastrarPaciente;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PacienteJPA {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    @Embedded
    private EnderecoJPA enderecoJPA;

    private Boolean ativo;

    public PacienteJPA(CadastrarPaciente dadosPaciente) {
        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.cpf = dadosPaciente.cpf();
        this.telefone = dadosPaciente.telefone();
        this.enderecoJPA = new EnderecoJPA(dadosPaciente.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(AtualizarPaciente dados) {
        if (dados.nome() != null)
            this.nome = dados.nome();

        if (dados.telefone() != null)
            this.telefone = dados.telefone();

        if (dados.endereco() != null)
            enderecoJPA.atualizarInformacoes(dados.endereco());
    }

    public void inativar() {
        this.ativo = false;
    }

}
