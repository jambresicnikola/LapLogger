package hr.tvz.laploggerapp.service.impl;

import hr.tvz.laploggerapp.command.TrackCommand;
import hr.tvz.laploggerapp.command.TrackPatchCommand;
import hr.tvz.laploggerapp.dto.TrackDto;
import hr.tvz.laploggerapp.exception.ResourceNotFoundException;
import hr.tvz.laploggerapp.mapper.TrackMapper;
import hr.tvz.laploggerapp.model.Track;
import hr.tvz.laploggerapp.repository.TrackRepository;
import hr.tvz.laploggerapp.service.TrackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
    private static final String TRACK_NOT_FOUND_WITH_ID_MESSAGE = "Track not found with id ";

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
                .orElseThrow(() -> new ResourceNotFoundException(TRACK_NOT_FOUND_WITH_ID_MESSAGE + id));
    }

    @Override
    public TrackDto createTrack(TrackCommand trackCommand) {
        Track savedTrack = trackRepository.save(trackMapper.toEntity(trackCommand));

        return trackMapper.toDto(savedTrack);
    }

    @Override
    public void deleteTrackById(Long id) {
        if (!trackRepository.existsById(id)) {
            throw new ResourceNotFoundException(TRACK_NOT_FOUND_WITH_ID_MESSAGE + id);
        }

        trackRepository.deleteById(id);
    }

    @Override
    public TrackDto updateTrack(Long id, TrackCommand trackCommand) {
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TRACK_NOT_FOUND_WITH_ID_MESSAGE + id));

        trackMapper.updateTrackFromCommand(trackCommand, track);
        Track updatedTrack = trackRepository.save(track);

        return trackMapper.toDto(updatedTrack);
    }

    @Override
    public TrackDto patchTrack(Long id, TrackPatchCommand trackPatchCommand) {
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TRACK_NOT_FOUND_WITH_ID_MESSAGE + id));

        trackMapper.updateTrackFromPatchCommand(trackPatchCommand, track);
        Track updatedTrack = trackRepository.save(track);

        return trackMapper.toDto(updatedTrack);
    }
}
