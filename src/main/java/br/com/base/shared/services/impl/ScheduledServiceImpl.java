package br.com.base.shared.services.impl;

import br.com.base.shared.services.ScheduledService;
import br.com.tv.domain.services.PresentationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ScheduledServiceImpl implements ScheduledService {

    private final PresentationService presentationService;

    @Scheduled(cron = "59 59 23 * * *")
    public void taskDeletePresentationByDeletedAt() { presentationService.deleteByDate(); }
}
