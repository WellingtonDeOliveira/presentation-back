package br.com.base.authentication.domain.services;

import br.com.base.authentication.api.controllers.auth.v1.models.DTOs.LoginRequestDTO;
import br.com.base.authentication.api.controllers.auth.v1.models.DTOs.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO auth(LoginRequestDTO request);
}
