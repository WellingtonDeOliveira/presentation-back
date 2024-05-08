package br.com.base.shared.models.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;
import java.util.List;

@Schema(name = "ProblemDTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProblemDTO(
        @Schema(example = "0")
        Integer status,
        @Schema(example = "2000-01-01T00:00:00Z")
        OffsetDateTime timestamp,
        @Schema(example = "https://localhost/invalid-data")
        String type,
        @Schema(example = "Invalid data")
        String title,
        @Schema(example = "One or more fields are invalid. Fill in correctly and try again.")
        String detail,
        @Schema(example = "One or more fields are invalid. Fill in correctly and try again.")
        String userMessage,
        @Schema(example = "List of objects or fields that generated or error (optional)")
        List<ProblemObjectDTO> objects
) {
    public ProblemDTO(Builder builder) {
        this(builder.status, builder.timestamp, builder.type, builder.title, builder.detail, builder.userMessage, builder.objects);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer status;
        private OffsetDateTime timestamp;
        private String type;
        private String title;
        private String detail;
        private String userMessage;
        private List<ProblemObjectDTO> objects;

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder timestamp(OffsetDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public Builder objects(List<ProblemObjectDTO> objects) {
            this.objects = objects;
            return this;
        }

        public ProblemDTO build() {
            return new ProblemDTO(this);
        }
    }
}
