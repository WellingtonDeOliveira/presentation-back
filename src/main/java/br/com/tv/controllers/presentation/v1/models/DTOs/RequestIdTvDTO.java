package br.com.tv.controllers.presentation.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "RequestIdTvDTO")
public record RequestIdTvDTO(
        @Schema(description = "Tv id", example = "00000000-0000-0000-0000-000000000000") UUID id
) {
}
