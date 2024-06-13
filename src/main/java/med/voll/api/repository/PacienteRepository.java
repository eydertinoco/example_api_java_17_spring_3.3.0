package med.voll.api.repository;

import med.voll.api.jpa.PacienteJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteJPA, Long> {
}
