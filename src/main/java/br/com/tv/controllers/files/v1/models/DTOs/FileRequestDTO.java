package br.com.tv.controllers.files.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

@Schema(name = "UpdateFileToDirectory")
public record FileRequestDTO(
        @Schema(description = "Time in seconds", example = "10") @NotBlank @Size(max = 2) @NotEmpty String time,
        @Schema(description = "Date to delete", example = "2000-01-01T00:00:00Z") @NotBlank @NotEmpty OffsetDateTime deletedAt,
        @Schema(description = "-.", example = "MultipatFile[]") List<MultipartFile> files) {
}
