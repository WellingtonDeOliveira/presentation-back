package br.com.base.authentication.configurations;

import br.com.base.authentication.domain.repositories.UserRepository;
import br.com.base.authentication.domain.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        var tokenO = tokenService.extractToken(request);
        if (tokenO.isPresent()) {
            String token = tokenO.get();
            if (tokenService.isValid(token)) {
                authenticate(token);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        var usernameO = tokenService.extractUsername(token);
        if (usernameO.isPresent()) {
            var loggedUser = userRepository.findByUsername(usernameO.get());
            if (loggedUser.isPresent()) {
                var authorities = tokenService.extractRoles(token);
                var authentication = new UsernamePasswordAuthenticationToken(usernameO.get(), loggedUser.get().getId(), authorities);
                authentication.setDetails(loggedUser);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }
}
