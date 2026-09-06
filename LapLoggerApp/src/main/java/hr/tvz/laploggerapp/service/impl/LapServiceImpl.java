package hr.tvz.laploggerapp.service.impl;

import hr.tvz.laploggerapp.command.LapCommand;
import hr.tvz.laploggerapp.command.LapPatchCommand;
import hr.tvz.laploggerapp.dto.LapDto;
import hr.tvz.laploggerapp.exception.ResourceNotFoundException;
import hr.tvz.laploggerapp.mapper.LapMapper;
import hr.tvz.laploggerapp.model.Lap;
import hr.tvz.laploggerapp.model.Session;
import hr.tvz.laploggerapp.repository.LapRepository;
import hr.tvz.laploggerapp.repository.SessionRepository;
import hr.tvz.laploggerapp.service.LapService;
import hr.tvz.laploggerapp.util.ErrorMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LapServiceImpl implements LapService {
    private final LapRepository lapRepository;
    private final LapMapper lapMapper;
    private final SessionRepository sessionRepository;

    public LapServiceImpl(LapRepository lapRepository, LapMapper lapMapper, SessionRepository sessionRepository) {
        this.lapRepository = lapRepository;
        this.lapMapper = lapMapper;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public LapDto fetchLapById(Long id) {
        return lapRepository.findById(id).map(lapMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.LAP_NOT_FOUND.getMessage() + id));
    }

    @Override
    public List<LapDto> fetchAllLapsBySessionId(Long sessionId) {
        if (!sessionRepository.existsById(sessionId)) {
            throw new ResourceNotFoundException(ErrorMessage.SESSION_NOT_FOUND.getMessage() + sessionId);
        }

        return lapMapper.toDtoList(lapRepository.findAllBySessionId(sessionId));
    }

    @Override
    public LapDto createLap(LapCommand lapCommand) {
        Session session = sessionRepository.findById(lapCommand.sessionId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SESSION_NOT_FOUND.getMessage() +  lapCommand.sessionId()));

        Lap lap = lapMapper.toEntity(lapCommand);
        lap.setSession(session);

        lap = lapRepository.save(lap);

        return lapMapper.toDto(lap);
    }

    @Override
    public void deleteLapById(Long id) {
        if (!lapRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorMessage.LAP_NOT_FOUND.getMessage() + id);
        }

        lapRepository.deleteById(id);
    }

    @Override
    public LapDto updateLap(Long id, LapCommand lapCommand) {
        Lap lap = lapRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.LAP_NOT_FOUND.getMessage() + id));

        Session session = sessionRepository.findById(lapCommand.sessionId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SESSION_NOT_FOUND.getMessage() + lapCommand.sessionId()));

        lapMapper.updateLapFromLapCommand(lapCommand, lap);
        lap.setSession(session);

        lap = lapRepository.save(lap);

        return lapMapper.toDto(lap);
    }

    @Override
    public LapDto patchLap(Long id, LapPatchCommand lapPatchCommand) {
        Lap lap = lapRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.LAP_NOT_FOUND.getMessage() + id));

        lapMapper.updateLapFromPatchCommand(lapPatchCommand, lap);

        if (lapPatchCommand.sessionId() != null) {
            Session session = sessionRepository.findById(lapPatchCommand.sessionId())
                    .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SESSION_NOT_FOUND.getMessage() + lapPatchCommand.sessionId()));

            lap.setSession(session);
        }

        lap = lapRepository.save(lap);

        return lapMapper.toDto(lap);
    }
}
