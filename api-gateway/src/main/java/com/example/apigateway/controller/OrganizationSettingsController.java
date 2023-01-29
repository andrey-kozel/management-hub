package com.example.apigateway.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.example.apigateway.converter.OrganizationSettingsConverter;
import com.example.apigateway.dto.OrganizationSettingsDto;
import com.example.apigateway.dto.OrganizationSettingsRequestDto;
import com.example.apigateway.dto.OrganizationSettingsResponseDto;
import com.example.apigateway.facades.OrgSvcFacade;
import com.example.apigateway.model.OrganizationSettings;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Data
@RequiredArgsConstructor
@RestController
@Slf4j
@ControllerAdvice
@RequestMapping("/api/v1/organization/settings")
public class OrganizationSettingsController {

    private final OrgSvcFacade facade;

    private final OrganizationSettingsConverter converter;

    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationSettings> saveAccessToken(
        @RequestBody @Valid final OrganizationSettingsRequestDto requestDto,
        @AuthenticationPrincipal final OAuth2User principal
    ) {
        Optional<Integer> organizationId = Optional.ofNullable(principal.getAttribute("organizationId"));
        if (organizationId.isPresent()) {
            OrganizationSettingsDto organizationSettingsDto = converter.requestToDto(requestDto);
            organizationSettingsDto.setOrganizationId(organizationId.get());
            return ResponseEntity.ok(facade.save(organizationSettingsDto));
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<OrganizationSettingsResponseDto> getAccessToken(
        @AuthenticationPrincipal final OAuth2User principal
    ) {
        Optional<Integer> organizationId = Optional.ofNullable(principal.getAttribute("organizationId"));
        if (organizationId.isPresent()) {
            OrganizationSettingsResponseDto dto = OrganizationSettingsResponseDto
                    .builder()
                    .accessToken(facade.getAccessToken(organizationId.get()))
                    .build();
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().build();
    }
}
