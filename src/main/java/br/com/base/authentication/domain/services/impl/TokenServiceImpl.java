package br.com.base.authentication.domain.services.impl;

import br.com.base.authentication.domain.models.entities.UserEntity;
import br.com.base.authentication.domain.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${api.security.jwt.secret-key}")
    private String secret;
    @Value("${api.security.jwt.expiration}")
    private Long expiration;
    @Value("${api.security.jwt.issuer}")
    private String issuer;

    @Override
    public String generate(UserEntity user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(issuer)
                .setIssuedAt(generateIssuedDate())
                .setExpiration(generateExpirationDate())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .claim("roles", user.getAllRoles())
                .compact();
    }

    @Override
    public boolean isValid(String token) {
        var claimsO = extractAllClaims(token);
        return claimsO.filter(this::isValidClaims).isPresent();
    }

    @Override
    public Optional<String> extractUsername(String token) {
        var claimsO = extractAllClaims(token);
        return claimsO.map(Claims::getSubject);
    }

    @Override
    public Set<GrantedAuthority> extractRoles(String token) {
        var claimsO = extractAllClaims(token);
        var rolesO = claimsO.map(c -> c.get("roles"));
        if (rolesO.isPresent()) {
            var roles = (List<String>) rolesO.get();
            return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableSet());
        }
        return new HashSet<>();
    }

    @Override
    public Optional<String> extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            token = token.replace("Bearer ", "");
        }
        return Optional.ofNullable(token);
    }

    private Optional<Claims> extractAllClaims(String token) {
        try {
            return Optional.of(
                    Jwts.parserBuilder()
                            .setSigningKey(getSignInKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody()
            );
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    private boolean isValidClaims(Claims claims) {
        return !claims.getSubject().isEmpty()
                && claims.getIssuer().equals(this.issuer)
                && claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Date generateIssuedDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration);
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
