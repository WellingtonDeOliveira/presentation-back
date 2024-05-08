package br.com.base.authentication.domain.services;

import br.com.base.authentication.domain.models.entities.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Optional;
import java.util.Set;

public interface TokenService {
    String generate(UserEntity user);

    boolean isValid(String token);

    Optional<String> extractToken(HttpServletRequest request);

    Optional<String> extractUsername(String token);

    Set<GrantedAuthority> extractRoles(String token);
}
