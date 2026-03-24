package br.com.guerlak.eventhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EventRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String title,

        @NotBlank(message = "Os detalhes são obrigatórios")
        String details,

        @NotNull(message = "O número máximo de participantes é obrigatório")
        @Positive(message = "O número de participantes deve ser maior que zero")
        Integer maximumAttendees


) {}