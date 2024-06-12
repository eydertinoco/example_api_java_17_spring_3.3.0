package med.voll.api.repository;

import med.voll.api.jpa.MedicoJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoJPA, Long> {
}
