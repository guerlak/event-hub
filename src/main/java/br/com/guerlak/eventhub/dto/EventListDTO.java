package br.com.guerlak.eventhub.dto;

import java.util.List;

public record EventListDTO(List<EventDetailDTO> events) {}