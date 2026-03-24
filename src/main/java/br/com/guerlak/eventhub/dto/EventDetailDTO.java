package br.com.guerlak.eventhub.dto;

import java.util.UUID;

public record EventDetailDTO(
    UUID id,
    String title,
    String details,
    String slug,
    Integer maximumAttendees,
    Integer attendeesCount
){}
