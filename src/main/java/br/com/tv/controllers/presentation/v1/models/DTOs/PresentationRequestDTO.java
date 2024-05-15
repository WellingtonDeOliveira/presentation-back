package br.com.tv.controllers.presentation.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Schema(name = "CratedPresentation")
public record PresentationRequestDTO (
        @Schema(description = "list by tvs id", example = "[\"00000000-0000-0000-0000-000000000000\"]") @NotEmpty Set<UUID> tvsId,
        @Schema(description = "Name Presentation", example = "Apresentação") @NotBlank @NotEmpty String name,
        @Schema(description = "Time in seconds", example = "10") @NotBlank @Size(max = 2) @NotEmpty String time,
        @Schema(description = "Date to delete", example = "2000-01-01T00:00:00Z") @NotBlank @NotEmpty OffsetDateTime deletedAt,
        @Schema(description = "-.", example = "MultipatFile[]") List<MultipartFile> files) {
}
