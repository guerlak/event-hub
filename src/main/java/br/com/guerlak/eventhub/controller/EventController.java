package br.com.guerlak.eventhub.controller;

import br.com.guerlak.eventhub.domain.event.Event;
import br.com.guerlak.eventhub.dto.EventDetailDTO;
import br.com.guerlak.eventhub.dto.EventListDTO;
import br.com.guerlak.eventhub.dto.EventRequestDTO;
import br.com.guerlak.eventhub.dto.EventResponseDTO;
import br.com.guerlak.eventhub.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> create(@RequestBody @Valid EventRequestDTO body, UriComponentsBuilder uriBuilder) {
        Event newEvent = this.eventService.createEvent(body);

        // Boa prática: Retornar o status 201 (Created) e a URL do novo recurso
        var uri = uriBuilder.path("/events/{id}").buildAndExpand(newEvent.getId()).toUri();
        return ResponseEntity.created(uri).body(new EventResponseDTO(newEvent.getId()));
    }

    // Adicione este método dentro do EventController.java

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailDTO> getEvent(@PathVariable UUID id) {
        EventDetailDTO event = this.eventService.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<EventListDTO> getAll() {
        EventListDTO allEvents = this.eventService.getAllEvents();
        return ResponseEntity.ok(allEvents);
    }
}