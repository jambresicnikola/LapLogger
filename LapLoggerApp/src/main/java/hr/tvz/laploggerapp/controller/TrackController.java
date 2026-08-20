package hr.tvz.laploggerapp.controller;

import hr.tvz.laploggerapp.command.TrackCommand;
import hr.tvz.laploggerapp.command.TrackPatchCommand;
import hr.tvz.laploggerapp.dto.TrackDto;
import hr.tvz.laploggerapp.service.TrackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {
    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public ResponseEntity<List<TrackDto>> fetchAllTracks() {
        return ResponseEntity.ok(trackService.fetchAllTracks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDto> fetchTrackById(@PathVariable Long id) {
        return ResponseEntity.ok(trackService.fetchTrackById(id));
    }

    @PostMapping
    public ResponseEntity<TrackDto> createTrack(@Valid @RequestBody TrackCommand trackCommand) {
        TrackDto createdTrackDto = trackService.createTrack(trackCommand);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTrackDto.id())
                .toUri();

        return ResponseEntity.created(location).body(createdTrackDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrackById(@PathVariable Long id) {
        trackService.deleteTrackById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackDto> updateTrack(@PathVariable Long id, @Valid @RequestBody TrackCommand trackCommand) {
        return ResponseEntity.ok(trackService.updateTrack(id, trackCommand));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TrackDto> patchTrack(
            @PathVariable Long id, @Valid @RequestBody TrackPatchCommand trackPatchCommand) {
        return ResponseEntity.ok(trackService.patchTrack(id, trackPatchCommand));
    }
}
