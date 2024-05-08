package br.com.base.authentication.api.controllers.auth.v1;

import br.com.base.authentication.api.controllers.auth.v1.models.DTOs.LoginRequestDTO;
import br.com.base.authentication.api.controllers.auth.v1.models.DTOs.LoginResponseDTO;
import br.com.base.authentication.api.controllers.auth.v1.annotations.LoginEndpoint;
import br.com.base.authentication.domain.services.AuthService;
import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@OpenApiController(name = "Authentication")
@ApiController(path = "/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @LoginEndpoint
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        var response = authService.auth(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
