package hr.tvz.laploggerapp.controller;

import hr.tvz.laploggerapp.command.SessionCommand;
import hr.tvz.laploggerapp.command.SessionPatchCommand;
import hr.tvz.laploggerapp.dto.SessionDto;
import hr.tvz.laploggerapp.service.SessionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<Page<SessionDto>> fetchAllSessions(Pageable pageable) {
        return ResponseEntity.ok(sessionService.fetchAllSessions(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> fetchSessionById(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.fetchSessionById(id));
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<SessionDto>> fetchAllSessionsByCarId(@PathVariable Long carId) {
        return ResponseEntity.ok(sessionService.fetchAllSessionsByCarId(carId));
    }

    @PostMapping
    public ResponseEntity<SessionDto> createSession(@Valid @RequestBody SessionCommand sessionCommand) {
        SessionDto savedSession = sessionService.createSession(sessionCommand);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSession.id())
                .toUri();

        return ResponseEntity.created(location).body(savedSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessionById(@PathVariable Long id) {
        sessionService.deleteSessionById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionDto> updateSession(@PathVariable Long id, @Valid @RequestBody SessionCommand sessionCommand) {
        return ResponseEntity.ok(sessionService.updateSession(id, sessionCommand));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SessionDto> patchSession(
            @PathVariable Long id, @Valid @RequestBody SessionPatchCommand sessionPatchCommand) {
        return ResponseEntity.ok(sessionService.patchSession(id, sessionPatchCommand));
    }
}
