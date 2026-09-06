package hr.tvz.laploggerapp.service;

import hr.tvz.laploggerapp.command.SessionCommand;
import hr.tvz.laploggerapp.command.SessionPatchCommand;
import hr.tvz.laploggerapp.dto.SessionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SessionService {
    Page<SessionDto> fetchAllSessions(Pageable pageable);
    SessionDto fetchSessionById(Long id);
    List<SessionDto> fetchAllSessionsByCarId(Long carId);
    SessionDto createSession(SessionCommand sessionCommand);
    void deleteSessionById(Long id);
    SessionDto updateSession(Long id, SessionCommand sessionCommand);
    SessionDto patchSession(Long id, SessionPatchCommand sessionPatchCommand);
}
