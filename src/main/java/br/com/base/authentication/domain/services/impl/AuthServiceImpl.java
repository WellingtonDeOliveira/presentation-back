package br.com.base.authentication.domain.services.impl;

import br.com.base.authentication.api.controllers.auth.v1.models.DTOs.LoginRequestDTO;
import br.com.base.authentication.domain.services.TokenService;
import br.com.base.authentication.api.controllers.auth.v1.models.DTOs.LoginResponseDTO;
import br.com.base.authentication.domain.services.AuthService;
import br.com.base.authentication.domain.models.entities.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public LoginResponseDTO auth(LoginRequestDTO request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var user = (UserEntity) auth.getPrincipal();
        var token = tokenService.generate(user);
        return buildLoginResponseDTO(token, user);
    }

    private LoginResponseDTO buildLoginResponseDTO(String token, UserEntity user) {
        return LoginResponseDTO.builder()
                .token(token)
                .nickname(user.getUsername())
                .campus(user.getCampus())
                .roles(user.getAllRoles())
                .groups(user.getGroupsNames())
                .build();
    }
}
