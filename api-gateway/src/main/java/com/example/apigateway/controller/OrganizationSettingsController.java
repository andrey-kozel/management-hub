package com.example.apigateway.controller;

import com.example.apigateway.converter.OrganizationSettingsConverter;
import com.example.apigateway.dto.OrganizationSettingsDto;
import com.example.apigateway.dto.OrganizationSettingsRequestDto;
import com.example.apigateway.dto.OrganizationSettingsResponseDto;
import com.example.apigateway.facades.OrgSvcFacade;
import com.example.apigateway.model.OrganizationSettings;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public ResponseEntity<OrganizationSettings> saveAccessToken(@RequestBody @Valid OrganizationSettingsRequestDto requestDto,
                                                                @AuthenticationPrincipal OAuth2User principal) {
        Optional<Integer> organizationId = Optional.ofNullable(principal.getAttribute("organizationId"));
        if (organizationId.isPresent()) {
            OrganizationSettingsDto organizationSettingsDto = converter.requestToDto(requestDto);
            organizationSettingsDto.setOrganizationId(organizationId.get());
            return ResponseEntity.ok(facade.save(organizationSettingsDto));
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<OrganizationSettingsResponseDto> getAccessToken(@AuthenticationPrincipal OAuth2User principal) {
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
