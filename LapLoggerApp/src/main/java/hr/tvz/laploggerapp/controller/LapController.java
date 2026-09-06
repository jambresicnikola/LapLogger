package hr.tvz.laploggerapp.controller;

import hr.tvz.laploggerapp.command.LapCommand;
import hr.tvz.laploggerapp.command.LapPatchCommand;
import hr.tvz.laploggerapp.dto.LapDto;
import hr.tvz.laploggerapp.service.LapService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/laps")
public class LapController {
    private final LapService lapService;

    public LapController(LapService lapService) {
        this.lapService = lapService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LapDto> fetchLapById(@PathVariable Long id) {
        return ResponseEntity.ok(lapService.fetchLapById(id));
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<LapDto>> fetchAllLapsBySessionId(@PathVariable Long sessionId) {
        return ResponseEntity.ok(lapService.fetchAllLapsBySessionId(sessionId));
    }

    @PostMapping
    public ResponseEntity<LapDto> createLap(@Valid @RequestBody LapCommand lapCommand) {
        LapDto createdLapDto = lapService.createLap(lapCommand);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLapDto.id())
                .toUri();

        return ResponseEntity.created(location).body(createdLapDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLapById(@PathVariable Long id) {
        lapService.deleteLapById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LapDto> updateLap(@PathVariable Long id, @Valid @RequestBody LapCommand lapCommand) {
        return ResponseEntity.ok(lapService.updateLap(id, lapCommand));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LapDto> patchLap(@PathVariable Long id, @Valid @RequestBody LapPatchCommand lapPatchCommand) {
        return ResponseEntity.ok(lapService.patchLap(id, lapPatchCommand));
    }
}
