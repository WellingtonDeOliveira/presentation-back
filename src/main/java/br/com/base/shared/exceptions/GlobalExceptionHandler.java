package br.com.base.shared.exceptions;

import br.com.base.shared.models.enums.ProblemType;
import br.com.base.shared.utils.DateTimeUtil;
import br.com.base.shared.models.DTOs.ProblemDTO;
import br.com.base.shared.services.InternalizationService;
import br.com.base.shared.models.DTOs.ProblemObjectDTO;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final InternalizationService internalization;

    public GlobalExceptionHandler(InternalizationService internalization) {
        this.internalization = internalization;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex) {
        try {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
            String detail = ex.getMessage();

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException ex) {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ProblemType problemType = ProblemType.BUSINESS_ERROR;
            String detail = ex.getMessage();

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ProblemType problemType = ProblemType.INVALID_PARAMETER;

            String detail = internalization.getMessage(
                    "URLReceivedInvalidTypeParameter",
                    ex.getName(), ex.getValue(),
                    Objects.requireNonNull(ex.getRequiredType()).getSimpleName()
            );

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(internalization.getMessage("GenericErrorToUser"))
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return handleBindException(ex);
    }

    @ExceptionHandler(BindException.class)
    private ResponseEntity<Object> handleBindException(BindException ex) {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ProblemType problemType = ProblemType.INVALID_DATA;
            String detail = internalization.getMessage("FieldsAreInvalid");

            List<ProblemObjectDTO> problemObjects = extractProblems(ex.getBindingResult());

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .objects(problemObjects)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        try {
            Throwable rootCause = ExceptionUtils.getRootCause(ex);

            if (rootCause instanceof InvalidFormatException) {
                return handleInvalidFormatException((InvalidFormatException) rootCause);
            } else if (rootCause instanceof PropertyBindingException) {
                return handlePropertyBindingException((PropertyBindingException) rootCause);
            }

            HttpStatus status = HttpStatus.BAD_REQUEST;
            ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
            String detail = internalization.getMessage("RequestBodyInvalid");

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(internalization.getMessage("GenericErrorToUser"))
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex) {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            String path = joinPath(ex.getPath());
            ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
            String detail = internalization.getMessage(
                    "PropertyReceivedInvalidType",
                    path,
                    ex.getValue(),
                    ex.getTargetType().getSimpleName()
            );

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(internalization.getMessage("GenericErrorToUser"))
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(PropertyBindingException.class)
    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex) {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            String path = joinPath(ex.getPath());
            ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
            String detail = internalization.getMessage("PropertyDoesNotExist", path);

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(internalization.getMessage("GenericErrorToUser"))
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException() {
        try {
            HttpStatus status = HttpStatus.FORBIDDEN;
            ProblemType problemType = ProblemType.ACCESS_DENIED;
            String detail = internalization.getMessage("UserDontHavePermission");

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException() {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ProblemType problemType = ProblemType.INVALID_DATA;
            String detail = internalization.getMessage("InvalidUsernamePassword");

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String detail = internalization.getMessage("GenericErrorToUser");

        ex.printStackTrace();

        ProblemDTO problem = problemBuilder(problemType)
                .status(status.value())
                .detail(detail)
                .userMessage(detail)
                .build();

        return new ResponseEntity<>(problem, status);
    }

    private ProblemDTO.Builder problemBuilder(ProblemType problemType) {
        return ProblemDTO.builder()
                .timestamp(DateTimeUtil.nowZoneUTC())
                .type(problemType.getUri())
                .title(internalization.getMessage(problemType.getTitle()));
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
                .map(Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

    private List<ProblemObjectDTO> extractProblems(BindingResult result) {
        return result.getAllErrors().stream().map(objectError -> {
                    String name = objectError.getObjectName();
                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }
                    return new ProblemObjectDTO(name, internalization.getMessage(objectError));
                })
                .collect(Collectors.toList());
    }
}
