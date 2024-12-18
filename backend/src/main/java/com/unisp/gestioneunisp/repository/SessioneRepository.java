package com.unisp.gestione.repositories;

import com.unisp.gestione.models.Sessione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;

public interface SessioneRepository extends JpaRepository<Sessione, Long> {
    Optional<Sessione> findByToken(String token);
    void deleteByDataScadenzaBefore(LocalDateTime date);
    Optional<Sessione> findByMembroIdAndDataScadenzaAfter(Long membroId, LocalDateTime now);
}
