package hr.tvz.laploggerapp.service.impl;

import hr.tvz.laploggerapp.command.TrackCommand;
import hr.tvz.laploggerapp.command.TrackPatchCommand;
import hr.tvz.laploggerapp.dto.TrackDto;
import hr.tvz.laploggerapp.exception.ResourceNotFoundException;
import hr.tvz.laploggerapp.mapper.TrackMapper;
import hr.tvz.laploggerapp.model.Track;
import hr.tvz.laploggerapp.repository.TrackRepository;
import hr.tvz.laploggerapp.service.TrackService;
import hr.tvz.laploggerapp.util.ErrorMessage;
import hr.tvz.laploggerapp.util.RepositoryUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;

    public TrackServiceImpl(TrackRepository trackRepository, TrackMapper trackMapper) {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
    }


    @Override
    public List<TrackDto> fetchAllTracks() {
        return trackMapper.toDto(trackRepository.findAll());
    }

    @Override
    public TrackDto fetchTrackById(Long id) {
        return trackRepository.findById(id).map(trackMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.TRACK_NOT_FOUND.getMessage() + id));
    }

    @Override
    public TrackDto createTrack(TrackCommand trackCommand) {
        Track track = trackRepository.save(trackMapper.toEntity(trackCommand));

        return trackMapper.toDto(track);
    }

    @Override
    public void deleteTrackById(Long id) {
        if (!trackRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorMessage.TRACK_NOT_FOUND.getMessage() + id);
        }

        trackRepository.deleteById(id);
    }

    @Override
    public TrackDto updateTrack(Long id, TrackCommand trackCommand) {
        Track track = RepositoryUtils.findOrThrow(
                trackRepository.findById(id),
                ErrorMessage.TRACK_NOT_FOUND.getMessage() + id
        );

        trackMapper.updateTrackFromCommand(trackCommand, track);
        track = trackRepository.save(track);

        return trackMapper.toDto(track);
    }

    @Override
    public TrackDto patchTrack(Long id, TrackPatchCommand trackPatchCommand) {
        Track track = RepositoryUtils.findOrThrow(
                trackRepository.findById(id),
                ErrorMessage.TRACK_NOT_FOUND.getMessage() + id
        );

        trackMapper.updateTrackFromPatchCommand(trackPatchCommand, track);
        track = trackRepository.save(track);

        return trackMapper.toDto(track);
    }
}
