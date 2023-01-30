package com.example.scheduler;

import com.example.scheduler.client.GithubClient;
import com.example.scheduler.client.SqsClient;
import com.example.scheduler.dto.RepoDto;
import com.example.scheduler.model.QueueMessage;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final static int ORGANIZATION_ID_LENGTH = 15;
    private final static int ACCESS_TOKEN_LENGTH = 13;

    private final SqsClient sqsClient;
    private final GithubClient githubClient;

    @Scheduled(fixedRate = 5000)
    public void schedule() {
        final ResponseEntity<String> response = ResponseEntity.ok().body("organisationId: " + 5151341 + ";" +
                " access_token: " + "sdo_fad5436sfa4ea3faefd1safea");
//                sqsClient.getMessage();

        if(response.getStatusCode() == HttpStatus.OK) {
            String message = response.getBody();

            String str[] = message.split("; ");

            String organizationId = str[0].substring(ORGANIZATION_ID_LENGTH, str[0].length());
            String accessToken = str[1].substring(ACCESS_TOKEN_LENGTH, str[1].length());

            List<RepoDto> repositories = githubClient.getRepositories(organizationId);
        }

    }
}
