package com.example.apigateway.controller;

import com.example.apigateway.dto.GetAccessTokenDto;
import com.example.apigateway.dto.SaveOrganizationSettingsDto;
import com.example.apigateway.facades.OrgSvcFacade;
import com.example.apigateway.service.OrganizationSettingsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Data
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/github-service/organization-settings/access-token")
public class OrganizationSettingsController {
    private final OrganizationSettingsService service;
    private final OrgSvcFacade facade;

    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAccessToken(@Validated @RequestBody SaveOrganizationSettingsDto dto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(facade.getRequestErrors(bindingResult));
        }
        return ResponseEntity.ok(service.save(dto));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{organizationId}")
    public ResponseEntity<GetAccessTokenDto> getAccessToken(final @PathVariable Long organizationId) {
        GetAccessTokenDto dto = new GetAccessTokenDto();
        dto.setAccessToken(service.getAccessToken(organizationId));
        return ResponseEntity.ok(dto);
    }
}
