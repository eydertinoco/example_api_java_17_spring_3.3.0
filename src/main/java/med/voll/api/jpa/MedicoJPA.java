package med.voll.api.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.enums.Especialidade;
import med.voll.api.record.Endereco;
import med.voll.api.record.Medico;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicoJPA {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private EnderecoJPA enderecoJPA;

    public MedicoJPA(Medico dadosMedico) {
        this.nome = dadosMedico.nome();
        this.email = dadosMedico.email();
        this.crm = dadosMedico.crm();
        this.especialidade = dadosMedico.especialidade();
        this.enderecoJPA = new EnderecoJPA(dadosMedico.endereco());
    }

}
