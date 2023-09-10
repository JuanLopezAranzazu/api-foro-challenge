package topics.challenge.api.domain.topics;

import jakarta.validation.constraints.NotNull;

public record TopicDTO(
        @NotNull(message = "Titulo es obligatorio")
        String title,
        @NotNull(message = "Mensaje es obligatorio")
        String message,
        @NotNull(message = "Curso es obligatorio")
        Course course) {
}
