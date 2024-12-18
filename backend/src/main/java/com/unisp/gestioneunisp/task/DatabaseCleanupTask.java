package com.unisp.gestione.tasks;

import com.unisp.gestione.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DatabaseCleanupTask {
    private final SessioneRepository sessioneRepository;
    private final NotificaRepository notificaRepository;
    private final PrenotazioneRepository prenotazioneRepository;

    @Scheduled(cron = "0 0 2 * * *") // Esegui ogni giorno alle 2:00
    @Transactional
    public void pulisciDatabase() {
        // Pulisci sessioni scadute
        sessioneRepository.deleteByDataScadenzaBefore(LocalDateTime.now());

        // Soft delete notifiche vecchie di 6 mesi
        LocalDateTime seiMesiFa = LocalDateTime.now().minusMonths(6);
        notificaRepository.softDeleteOldNotifications(seiMesiFa);

        // Soft delete prenotazioni vecchie
        LocalDateTime unMeseFa = LocalDateTime.now().minusMonths(1);
        prenotazioneRepository.softDeleteOldPrenotazioni(unMeseFa);
    }
}
