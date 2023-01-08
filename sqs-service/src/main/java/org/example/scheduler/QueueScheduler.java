package org.example.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.converter.QueueMessageConverter;
import org.example.service.QueueService;
import org.example.dto.QueueMessageDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueScheduler {

    private final QueueService queueService;

    private final QueueMessageConverter queueConverter;

    private final UserService userService;


    @Scheduled(cron = "* 0/30 * * * *")
    public void sendMessage() {
        for (Long orgId: userService.getOrganizations()) {
            try {
                QueueMessageDto queueMessageDto = queueConverter.toDto(orgId, queueService.getToken(orgId));
                queueService.sendMessage(queueMessageDto);
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
