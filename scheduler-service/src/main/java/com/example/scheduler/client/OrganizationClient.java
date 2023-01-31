package com.example.scheduler.client;

import com.example.scheduler.dto.OrganizationResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "organization", url = "${services.user-service.url}/api/v1/organizations")
public interface OrganizationClient {
    @GetMapping
    List<OrganizationResponse> getAll();
}
