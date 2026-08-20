package hr.tvz.laploggerapp.service;

import hr.tvz.laploggerapp.command.TrackCommand;
import hr.tvz.laploggerapp.command.TrackPatchCommand;
import hr.tvz.laploggerapp.dto.TrackDto;

import java.util.List;

public interface TrackService {
    List<TrackDto> fetchAllTracks();
    TrackDto fetchTrackById(Long id);
    TrackDto createTrack(TrackCommand trackCommand);
    void deleteTrackById(Long id);
    TrackDto updateTrack(Long id, TrackCommand trackCommand);
    TrackDto patchTrack(Long id, TrackPatchCommand trackPatchCommand);
}
