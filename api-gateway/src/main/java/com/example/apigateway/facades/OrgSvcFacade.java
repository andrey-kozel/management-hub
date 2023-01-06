package com.example.apigateway.facades;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class OrgSvcFacade {
    public List<String> getRequestErrors(BindingResult bindingResult) {
        List<String> errors = bindingResult
                .getFieldErrors()
                .stream()
                .map(x -> Optional.ofNullable(x.getDefaultMessage()).orElse("unknown error"))
                .toList();
        log.error("User entered invalid data: " + errors);
        return errors;
    }
}
