package org.example.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.converter.QueueConverter;
import org.example.service.QueueService;
import org.example.dto.QueueMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueScheduler {

    QueueService queueService;
    QueueConverter queueConverter;
    UserService userService;
    GithubApi githubApi;

    @Autowired
    public QueueScheduler(UserService userService, GithubApi githubApi) {
        this.userService = userService;
        this.githubApi = githubApi;
    }


    public String getToken(Long organisationId) {
        if (!githubApi.getOrgId().equals(organisationId)) {
            return null;
        }
        return githubApi.getAccessToken();
    }

    @Scheduled(cron = "* 0/30 * * * *")
    public void sendMessage() {
        for (Long orgId: userService.organisations()) {
            try {
                QueueMessageDto queueMessageDto = queueConverter.toDto(orgId, getToken(orgId));
                queueService.sendMessage(queueMessageDto);
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
