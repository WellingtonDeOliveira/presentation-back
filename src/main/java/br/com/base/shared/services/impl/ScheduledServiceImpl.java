package br.com.base.shared.services.impl;

import br.com.base.shared.services.ScheduledService;
import br.com.tv.domain.services.PresentationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ScheduledServiceImpl implements ScheduledService {

    private final PresentationService presentationService;
}
