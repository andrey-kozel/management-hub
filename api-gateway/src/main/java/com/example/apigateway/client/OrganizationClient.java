package com.example.apigateway.client;

import com.nimbusds.openid.connect.sdk.assurance.evidences.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "organization", url = "${services.organization.url}/api/v1/organizations")
public interface OrganizationClient {

    @PostMapping("/save")
    Organization save(@RequestParam("organizationName") final String organizationName);
}
