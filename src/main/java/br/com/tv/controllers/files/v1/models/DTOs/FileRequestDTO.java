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
        @Schema(description = "-.", example = "MultipatFile[]") List<MultipartFile> files) {
}
