package br.com.guerlak.eventhub.services;

import br.com.guerlak.eventhub.domain.event.Event;
import br.com.guerlak.eventhub.dto.EventDetailDTO;
import br.com.guerlak.eventhub.dto.EventListDTO;
import br.com.guerlak.eventhub.dto.EventRequestDTO;
import br.com.guerlak.eventhub.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Cria o construtor para injeção de dependência automática
public class EventService {

    private final EventRepository eventRepository;

    public Event createEvent(EventRequestDTO data) {
        Event newEvent = new Event();

        newEvent.setTitle(data.title());
        newEvent.setDetails(data.details());
        newEvent.setMaximumAttendees(data.maximumAttendees());

        // Lógica do Slug: "Workshop Java" -> "workshop-java"
        String slug = generateSlug(data.title());

        // Verificação de unicidade (Regra de Negócio Crucial)
        if (eventRepository.findBySlug(slug).isPresent()) {
            throw new RuntimeException("Já existe um evento com este título!");
        }

        newEvent.setSlug(slug);

        return eventRepository.save(newEvent);
    }

    private String generateSlug(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "") // Remove acentos
                .replaceAll("[^\\w\\s]", "") // Remove caracteres especiais
                .replaceAll("\\s+", "-")     // Substitui espaços por hífen
                .toLowerCase();              // Tudo em minúsculo
    }

    public EventDetailDTO getEventDetail(UUID eventId) {
        Event event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado com o ID: " + eventId));

        // Por enquanto, o contador de participantes será 0 (vamos implementar isso depois)
        return new EventDetailDTO(
                event.getId(),
                event.getTitle(),
                event.getDetails(),
                event.getSlug(),
                event.getMaximumAttendees(),
                0
        );
    }

    public EventListDTO getAllEvents() {
        List<EventDetailDTO> eventList = this.eventRepository.findAll().stream()
                .map(event -> new EventDetailDTO(
                        event.getId(),
                        event.getTitle(),
                        event.getDetails(),
                        event.getSlug(),
                        event.getMaximumAttendees(),
                        0 // Por enquanto 0, até implementarmos os participantes
                )).toList();

        return new EventListDTO(eventList);
    }
}