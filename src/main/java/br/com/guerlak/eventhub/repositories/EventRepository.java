package br.com.guerlak.eventhub.repositories;


import br.com.guerlak.eventhub.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    Optional<Event> findBySlug(String slug);
}