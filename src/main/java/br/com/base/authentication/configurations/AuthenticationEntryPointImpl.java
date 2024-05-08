package br.com.base.authentication.configurations;

import br.com.base.shared.models.enums.ProblemType;
import br.com.base.shared.utils.DateTimeUtil;
import br.com.base.shared.models.DTOs.ProblemDTO;
import br.com.base.shared.services.InternalizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    private final InternalizationService internalization;

    public AuthenticationEntryPointImpl(ObjectMapper objectMapper, InternalizationService internalization) {
        this.objectMapper = objectMapper;
        this.internalization = internalization;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        int status = HttpStatus.UNAUTHORIZED.value();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status);

        OutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, buildProblem(status));
        out.flush();
    }

    private ProblemDTO buildProblem(int status) {
        String detail = internalization.getMessage("UnauthenticatedUser");
        return ProblemDTO.builder()
                .status(status)
                .timestamp(DateTimeUtil.nowZoneUTC())
                .type(ProblemType.ACCESS_DENIED.getUri())
                .title(internalization.getMessage(ProblemType.ACCESS_DENIED.getTitle()))
                .detail(detail)
                .userMessage(detail)
                .build();
    }
}
