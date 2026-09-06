package hr.tvz.laploggerapp.service.impl;

import hr.tvz.laploggerapp.command.SessionCommand;
import hr.tvz.laploggerapp.command.SessionPatchCommand;
import hr.tvz.laploggerapp.dto.SessionDto;
import hr.tvz.laploggerapp.exception.ResourceNotFoundException;
import hr.tvz.laploggerapp.mapper.SessionMapper;
import hr.tvz.laploggerapp.model.Car;
import hr.tvz.laploggerapp.model.Session;
import hr.tvz.laploggerapp.model.Track;
import hr.tvz.laploggerapp.repository.CarRepository;
import hr.tvz.laploggerapp.repository.SessionRepository;
import hr.tvz.laploggerapp.repository.TrackRepository;
import hr.tvz.laploggerapp.service.SessionService;
import hr.tvz.laploggerapp.util.ErrorMessage;
import hr.tvz.laploggerapp.util.RepositoryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final CarRepository carRepository;
    private final TrackRepository trackRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, SessionMapper sessionMapper, CarRepository carRepository, TrackRepository trackRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.carRepository = carRepository;
        this.trackRepository = trackRepository;
    }

    @Override
    public Page<SessionDto> fetchAllSessions(Pageable pageable) {
        return sessionRepository.findAll(pageable).map(sessionMapper::toDto);
    }

    @Override
    public SessionDto fetchSessionById(Long id) {
        return sessionRepository.findById(id).map(sessionMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SESSION_NOT_FOUND.getMessage() + id));
    }

    @Override
    public List<SessionDto> fetchAllSessionsByCarId(Long carId) {
        if (!carRepository.existsById(carId)) {
            throw new ResourceNotFoundException(ErrorMessage.CAR_NOT_FOUND.getMessage() + carId);
        }

        return sessionMapper.toDtoList(sessionRepository.findAllByCarId(carId));
    }

    @Override
    public SessionDto createSession(SessionCommand sessionCommand) {
        Car car = RepositoryUtils.findOrThrow(
                carRepository.findById(sessionCommand.carId()),
                ErrorMessage.CAR_NOT_FOUND.getMessage() + sessionCommand.carId()
        );

        Track track = RepositoryUtils.findOrThrow(
                trackRepository.findById(sessionCommand.trackId()),
                ErrorMessage.TRACK_NOT_FOUND.getMessage() + sessionCommand.trackId()
        );

        Session session = sessionMapper.toEntity(sessionCommand);
        session.setCar(car);
        session.setTrack(track);

        session = sessionRepository.save(session);

        return sessionMapper.toDto(session);
    }

    @Override
    public void deleteSessionById(Long id) {
        if (!sessionRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorMessage.SESSION_NOT_FOUND.getMessage() + id);
        }

        sessionRepository.deleteById(id);
    }

    @Override
    public SessionDto updateSession(Long id, SessionCommand sessionCommand) {
        Session session = RepositoryUtils.findOrThrow(
                sessionRepository.findById(id),
                ErrorMessage.SESSION_NOT_FOUND.getMessage() + id
        );

        Car car = RepositoryUtils.findOrThrow(
                carRepository.findById(sessionCommand.carId()),
                ErrorMessage.CAR_NOT_FOUND.getMessage() + sessionCommand.carId()
        );

        Track track = RepositoryUtils.findOrThrow(
                trackRepository.findById(sessionCommand.trackId()),
                ErrorMessage.TRACK_NOT_FOUND.getMessage() + sessionCommand.trackId()
        );

        sessionMapper.updateSessionFromSessionCommand(sessionCommand, session);
        session.setCar(car);
        session.setTrack(track);

        session = sessionRepository.save(session);

        return sessionMapper.toDto(session);
    }

    @Override
    public SessionDto patchSession(Long id, SessionPatchCommand sessionPatchCommand) {
        Session session = RepositoryUtils.findOrThrow(
                sessionRepository.findById(id),
                ErrorMessage.SESSION_NOT_FOUND.getMessage() + id
        );

        if (sessionPatchCommand.carId() != null) {
            Car car = RepositoryUtils.findOrThrow(
                    carRepository.findById(sessionPatchCommand.carId()),
                    ErrorMessage.CAR_NOT_FOUND.getMessage() + sessionPatchCommand.carId()
            );

            session.setCar(car);
        }

        if (sessionPatchCommand.trackId() != null) {
            Track track = RepositoryUtils.findOrThrow(
                    trackRepository.findById(sessionPatchCommand.trackId()),
                    ErrorMessage.TRACK_NOT_FOUND.getMessage() + sessionPatchCommand.trackId()
            );

            session.setTrack(track);
        }

        sessionMapper.updateSessionFromPatchCommand(sessionPatchCommand, session);
        session = sessionRepository.save(session);

        return sessionMapper.toDto(session);
    }
}
